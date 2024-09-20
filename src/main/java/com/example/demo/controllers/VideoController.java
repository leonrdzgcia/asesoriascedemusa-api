package com.example.demo.controllers;

import com.example.demo.models.AsignacionModel;
import com.example.demo.services.AsignacionService;
import com.example.demo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping()
    public ArrayList<VideoModel> obtenerVideos(){
        return videoService.obtenerVideos();
    }

    @GetMapping("/video")
    public List<VideoModel> obtenerListVideos() {
        return this.videoService.buscarVideos();
    }
}
