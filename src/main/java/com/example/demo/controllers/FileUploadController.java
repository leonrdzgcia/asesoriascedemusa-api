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
    private List<ArchivosftpModel> sfiles;

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
            boolean uploaded = ftpClient.storeFile("/domains/asesoriascedemusa.com/public_html/assets/vid/"
                    + file.getOriginalFilename(), inputStream);
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



    @GetMapping("/archivos")
    public List<ArchivosftpModel> obtenerListaImagenes( @RequestParam("src") int src) {
        System.out.println("-- listaImagenes 1 " + src );// 1 video / 2 imagenes
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(600000);
        ftpClient.setDefaultTimeout(600000);
        List<ArchivosftpModel> archivos = null;
        try {

            try {
                /*client.connect(config.getHost(), config.getPort());
                client.setSoTimeout(600000);*/
                ftpClient.connect(ftpServer, ftpPort);// Conectar al servidor FTP
                ftpClient.setSoTimeout(600000);
                ftpClient.login(ftpUsername, ftpPassword);
            } catch (IOException ex) {
                System.out.println("Error in connecting en el tiempo de conexion: " + ex.getMessage());
                ex.printStackTrace();
            }
            //ftpClient.connect(ftpServer, ftpPort);// Conectar al servidor FTP
            //ftpClient.login(ftpUsername, ftpPassword);
            int replyCode = ftpClient.getReplyCode();// Verificar si la conexión fue exitosa
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Fallo en la conexión al servidor FTP");
                return null;
            }
            if (src  == 1) {// Navegar al directorio deseado
                System.out.println("-- es  1 VID ");
                ftpClient.changeWorkingDirectory("/domains/asesoriascedemusa.com/public_html/assets/img/vid/");
            }
            if (src  == 2) {
                System.out.println("--  es  2 IMG ");
                ftpClient.changeWorkingDirectory("/domains/asesoriascedemusa.com/public_html/assets/img/");
            }
            // Listar los archivos en el directorio
            System.out.println("-- listaImagenes 7 ");
            FTPFile[] files = ftpClient.listFiles();
            System.out.println("-- listaImagenes 8");
            String[] sfiles = null;
            System.out.println("-- listaImagenes 9 ");
            archivos = new ArrayList<>();
            System.out.println("-- listaImagenes 10");
            if (files != null) {
                System.out.println("-- listaImagenes 11 ");
                sfiles = new String[files.length];
                System.out.println("-- listaImagenes 12 ");
                for (int i = 0; i < files.length; i++) {
                    //System.out.println(sfiles[i] = files[i].getName());
                    archivos.add(new ArchivosftpModel(i, files[i].getName()));
                }
                System.out.println("-- listaImagenes 13 ");
            }
            System.out.println("FILES 2----");
            //System.out.println(files);
            //archivos = new ArrayList<>();
            System.out.println("-- ARRAY FILES 3");
            //System.out.println(archivos);
            ftpClient.logout();// Desconectar del servidor FTP
            ftpClient.disconnect();
            System.out.println("-- ARRAY FILES 4");
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


    @GetMapping("/listFiles")
    public List<String> listFiles() throws IOException {
        System.out.println("-- listFiles ");
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
    public String  eliminarArchivosG (@RequestParam("src") String src)  {
        boolean flag;
        try {
            flag = this.deleteFile(src);
            //System.out.println("-- deleteFile ");
            if (flag ) {
                System.out.println(flag);
                return "Archivo eliminado";
            }else {
                System.out.println(flag);
                return "Archivo no se a eliminado";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//return "Received: eliminarArchivosP";
    }


    @PostMapping("/deleteFileP")
    public String  eliminarArchivosP (@RequestParam("src") String src)  {
        boolean flag;
        try {
            flag = this.deleteFile(src);
            //System.out.println("-- deleteFile ");
            if (flag ) {
                System.out.println(flag);
                return "Archivo eliminado";
            }else {
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
