package com.example.demo.controllers;


import com.example.demo.services.StorageService;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("media")

public class MediaController {

    private StorageService storageService;
    private HttpServletRequest request;



    @PostMapping("upload")
    public Map<String, String> uploadFile (@RequestParam("file") MultipartFile multipartFile){
        System.out.println("-- CARGA ...");
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getSize());
        System.out.println("-- CARGA ...2");
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(path).toString();

        return Map.of("url", url);

    }


    public ResponseEntity<Resource> getFile (@PathVariable String filename) throws IOException{
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,contentType).body(file);
    }
}
