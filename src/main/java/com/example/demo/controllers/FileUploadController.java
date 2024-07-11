package com.example.demo.controllers;

import com.example.demo.models.ArchivosftpModel;
import com.example.demo.models.AsignacionModel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
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
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Archivo no válido", HttpStatus.BAD_REQUEST);
        }
        FTPClient ftpClient = new FTPClient();
        System.out.println("VALIDACIONES ");
        System.out.println(ftpServer+ftpPort+ftpUsername+ftpPassword);
        System.out.println("VALIDACIONES FIN");
        try (InputStream inputStream = file.getInputStream()) {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //boolean uploaded = ftpClient.storeFile("/domains/asesoriascedemusa.com/public_html/assets/img/" + file.getOriginalFilename(), inputStream);
            boolean uploaded = ftpClient.storeFile("/domains/asesoriascedemusa.com/public_html/assets/img/vid/" + file.getOriginalFilename(), inputStream);
            if (uploaded) {
                System.out.println("Archivo subido exitosamente");
                return new ResponseEntity<>("Archivo subido exitosamente", HttpStatus.OK);
            } else {
                System.out.println("Error al subir el archivo");
                return new ResponseEntity<>("Error al subir el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con el servidor FTP");
            return new ResponseEntity<>("Error al conectar con el servidor FTP", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping("/listaVideos")
    public List<ArchivosftpModel> obtenerListaVideos() {
        FTPClient ftpClient = new FTPClient();
        List<ArchivosftpModel> archivos = null;
        try {
            // Conectar al servidor FTP
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            // Verificar si la conexión fue exitosa
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Fallo en la conexión al servidor FTP");
                return null;
            }
            // Navegar al directorio deseado
            ftpClient.changeWorkingDirectory("/domains/asesoriascedemusa.com/public_html/assets/vid/");
            // Listar los archivos en el directorio
            FTPFile[] files = ftpClient.listFiles();
            System.out.println("FILES ----");
            System.out.println(files);
            archivos = new ArrayList<>();
            int index = 0;
            for (FTPFile file : files) {
                if (file.isFile()) {
                    index++;
                    System.out.println("Archivo: " + file.getName());
                    archivos.add(new ArchivosftpModel(index, file.getName()));
                } else if (file.isDirectory()) {
                    index++;
                    System.out.println("Directorio: " + file.getName());
                }
            }
            /*System.out.println("LONGITUD DE ARCHIVOS = "+ files.length);
            for (int i = 0; i < files.length; i++) {
            }*/
            System.out.println("-- ARRAY FILES ");
            System.out.println(archivos);
            // Desconectar del servidor FTP
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
            ex.printStackTrace();
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
        return archivos;
    }

    @GetMapping("/listaImagenes")
    public List<ArchivosftpModel> obtenerListaImagenes() {
        FTPClient ftpClient = new FTPClient();
        List<ArchivosftpModel> archivos = null;
        try {
            // Conectar al servidor FTP
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            // Verificar si la conexión fue exitosa
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Fallo en la conexión al servidor FTP");
                return null;
            }
            // Navegar al directorio deseado
            ftpClient.changeWorkingDirectory("/domains/asesoriascedemusa.com/public_html/assets/img/");
            // Listar los archivos en el directorio
            FTPFile[] files = ftpClient.listFiles();
            System.out.println("FILES ----");
            System.out.println(files);
            archivos = new ArrayList<>();
            int index = 0;
            for (FTPFile file : files) {
                if (file.isFile()) {
                    index++;
                    System.out.println("Archivo: " + file.getName());
                    archivos.add(new ArchivosftpModel(index, file.getName()));
                } else if (file.isDirectory()) {
                    index++;
                    System.out.println("Directorio: " + file.getName());
                }
            }
            /*System.out.println("LONGITUD DE ARCHIVOS = "+ files.length);
            for (int i = 0; i < files.length; i++) {
            }*/
            System.out.println("-- ARRAY FILES ");
            System.out.println(archivos);
            // Desconectar del servidor FTP
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
            ex.printStackTrace();
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
        return archivos;
    }
}
