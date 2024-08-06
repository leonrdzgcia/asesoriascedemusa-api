package com.example.demo.services;

import com.example.demo.models.ArchivosftpModel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FtpService {
    @Value("${ftp.server}")
    private String ftpServer;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    /*@Value("${ftp.base.directory}")
    private String ftpBaseDirectory;*/

    public List<ArchivosftpModel> listFiles(int src) throws IOException {
        FTPClient ftpClient = new FTPClient();
        List<ArchivosftpModel> fileList = new ArrayList<>();
        try {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();

            FTPFile[] files = new FTPFile[0];

            //FTPFile[] files = ftpClient.listFiles("/domains/asesoriascedemusa.com/public_html/assets/img/vid/");

            if (src  == 1) {// Navegar al directorio deseado
                files = ftpClient.listFiles("/domains/asesoriascedemusa.com/public_html/assets/img/vid/");
            }
            if (src  == 2) {
                files = ftpClient.listFiles("/domains/asesoriascedemusa.com/public_html/assets/img/");
            }

            System.out.println("CADENAS BACJ");
            System.out.println(files);
            System.out.println(files.length);
            /*for (FTPFile file : files) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }*/
            int cont = 0;
            for (int i = 0; i < files.length; i++) {
                System.out.println("INICIO FOR "+ i);
                //System.out.println( i + "/"+files[i].getName());
                if (files[i].getName().toString().equals(".") || files[i].getName().toString().equals("..")){
                    System.out.println( i + "/"+files[i].getName());
                    //fileList.add(new ArchivosftpModel(i, files[i].getName()));

                }else{
                    //System.out.println( i + "/"+files[i].getName());
                    fileList.add(new ArchivosftpModel(cont, files[i].getName()));
                    cont++;
                }
                System.out.println("FIN FOR ");
            }
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        return fileList;
    }
}