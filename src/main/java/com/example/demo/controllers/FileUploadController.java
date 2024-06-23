package com.example.demo.controllers;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

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
        System.out.println(ftpServer);
        System.out.println(ftpPort);
        System.out.println(ftpUsername);
        System.out.println(ftpPassword);
        System.out.println("VALIDACIONES FIN");
        try (InputStream inputStream = file.getInputStream()) {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            boolean uploaded = ftpClient.storeFile(
                    "/domains/asesoriascedemusa.com/public_html/assets/img/" + file.getOriginalFilename(), inputStream);
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
}
