package com.example.demo.controllers;

import com.example.demo.models.ArchivosftpModel;
import com.example.demo.models.AsignacionModel;
import com.example.demo.services.FtpService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

@RestController

public class FileUploadController {
    @Value("${ftp.server}")
    private String ftpServer;
    @Value("${ftp.port}")
    private int ftpPort;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.password}")
    private String ftpPassword;
    private List<ArchivosftpModel> sfiles;

    @Autowired
    private FtpService ftpService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile[] files,
            @RequestParam(value = "src", defaultValue = "2") int src) {
        System.out.println("-- /upload ");
        System.out.println("-- src: " + src);
        System.out.println("-- Número de archivos recibidos: " + files.length);
        if (files == null || files.length == 0) {
            return new ResponseEntity<>("No se recibieron archivos", HttpStatus.BAD_REQUEST);
        }
        // Validar que src sea 1 o 2
        if (src != 1 && src != 2) {
            return new ResponseEntity<>("Parámetro 'src' inválido. Debe ser 1 (imágenes) o 2 (videos)", HttpStatus.BAD_REQUEST);
        }
        // Determinar la ruta según el valor de src
        String ftpPath;
        if (src == 1) {
            // Ruta para imágenes
            ftpPath = "/domains/asesoriascedemusa.com/public_html/assets/img/";
            System.out.println("Guardando en ruta de imágenes");
        } else {
            // Ruta para videos (src == 2)
            ftpPath = "/domains/asesoriascedemusa.com/public_html/assets/img/vid/";
            System.out.println("Guardando en ruta de videos");
        }

        FTPClient ftpClient = new FTPClient();
        List<String> uploadedFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();
        try {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // Procesar cada archivo
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    failedFiles.add(file.getOriginalFilename() + " (archivo vacío)");
                    continue;
                }

                try (InputStream inputStream = file.getInputStream()) {
                    String fullPath = ftpPath + file.getOriginalFilename();
                    System.out.println("Subiendo archivo a: " + fullPath);

                    boolean uploaded = ftpClient.storeFile(fullPath, inputStream);

                    if (uploaded) {
                        System.out.println("Archivo subido exitosamente a: " + fullPath);
                        uploadedFiles.add(file.getOriginalFilename());
                    } else {
                        System.out.println("Error al subir el archivo: " + file.getOriginalFilename());
                        failedFiles.add(file.getOriginalFilename());
                    }
                } catch (IOException e) {
                    System.out.println("Error al procesar el archivo: " + file.getOriginalFilename());
                    failedFiles.add(file.getOriginalFilename() + " (error: " + e.getMessage() + ")");
                }
            }

            // Construir respuesta
            StringBuilder response = new StringBuilder();
            if (!uploadedFiles.isEmpty()) {
                response.append("Archivos subidos exitosamente: ").append(uploadedFiles.size())
                        .append(" a ").append(src == 1 ? "imágenes" : "videos");
            }
            if (!failedFiles.isEmpty()) {
                if (response.length() > 0) {
                    response.append(". ");
                }
                response.append("Archivos fallidos: ").append(failedFiles.size());
            }

            if (uploadedFiles.isEmpty() && !failedFiles.isEmpty()) {
                return new ResponseEntity<>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (!uploadedFiles.isEmpty()) {
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se procesaron archivos", HttpStatus.BAD_REQUEST);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con el servidor FTP");
            return new ResponseEntity<>("Error al conectar con el servidor FTP: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @GetMapping("/archivos")
    public List<ArchivosftpModel> obtenerListaImagenes(@RequestParam("src") int src) throws IOException {
        System.out.println("-- /archivos ");
        try {
            List<ArchivosftpModel> fileList = ftpService.listFiles(src);
            return fileList;
        } finally {
        }
    }
    @GetMapping("/listFiles")
    public List<String> listFiles() throws IOException {
        System.out.println("-- /listFiles ");

        FTPClient ftpClient = new FTPClient();
        List<String> fileNames = new ArrayList<>();
        try {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory("/domains/asesoriascedemusa.com/public_html/assets/vid/");
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        return fileNames;
    }
    @GetMapping("/deleteFileG")
    public String eliminarArchivosG(@RequestParam("src") String src) {
        System.out.println("-- /deleteFileG ");
        boolean flag;
        try {
            flag = this.deleteFile(src);
            //System.out.println("-- deleteFile ");
            if (flag) {
                System.out.println(flag);
                return "Archivo eliminado";
            } else {
                System.out.println(flag);
                return "Archivo no se a eliminado";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//return "Received: eliminarArchivosP";
    }
    @PostMapping("/deleteFileP")
    public String eliminarArchivosP(@RequestParam("src") String src) {
        System.out.println("-- /deleteFileP ");
        boolean flag;
        try {
            flag = this.deleteFile(src);
            //System.out.println("-- deleteFile ");
            if (flag) {
                System.out.println(flag);
                return "Archivo eliminado";
            } else {
                System.out.println(flag);
                return "Archivo no se a eliminado";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//return "Received: eliminarArchivosP";
    }
    public boolean deleteFile(String remoteFilePath) throws IOException {
        System.out.println(remoteFilePath);
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ftpServer, ftpPort);
        ftpClient.login(ftpUsername, ftpPassword);
        ftpClient.enterLocalPassiveMode();
        System.out.println("-- deleteFile 5");
        boolean deleted = ftpClient.deleteFile(remoteFilePath);
        System.out.println("-- deleteFile 6");
        System.out.println(deleted);
        /*if (!deleted) {throw new IOException("Could not delete file: " + ftpClient.getReplyString());}*/
        System.out.println("-- deleteFile 7");
        ftpClient.logout();
        ftpClient.disconnect();
        return deleted;
    }
}
