package com.example.demo.config;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class FTPFileSearch {
    /*public static void main(String[] args) {
        String server = "194.164.64.99";
        int port = 21;
        String user = "u255965900";
        String pass = "C@d@m@s@10";

        FTPClient ftpClient = new FTPClient();

        try {
            // Conectar al servidor FTP
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);

            // Verificar si la conexión fue exitosa
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Fallo en la conexión al servidor FTP");
                return;
            }

            // Navegar al directorio deseado
            ftpClient.changeWorkingDirectory("/domains/asesoriascedemusa.com/public_html/assets/img/vid/");

            // Listar los archivos en el directorio
            FTPFile[] files = ftpClient.listFiles();
            System.out.println("FILES ----");
            System.out.println(files);

            for (FTPFile file : files) {
                if (file.isFile()) {
                    System.out.println("Archivo: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directorio: " + file.getName());
                }
            }

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
        }*/
}