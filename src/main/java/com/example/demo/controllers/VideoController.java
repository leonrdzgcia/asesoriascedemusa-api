package com.example.demo.controllers;

import com.example.demo.models.AsignacionModel;
import com.example.demo.services.AsignacionService;
import com.example.demo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping()
    public ArrayList<VideoModel> obtenerVideos(){
        return videoService.obtenerVideos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoModel> obtenerVideoPorId(@PathVariable Long id) {
        Optional<VideoModel> video = videoService.obtenerVideoPorId(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(video.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/video")
    public List<VideoModel> obtenerListVideos() {
        return this.videoService.buscarVideos();
    }

    @PostMapping()
    public VideoModel guardarVideo(@RequestBody VideoModel video){
        return this.videoService.guardarVideo(video);
    }
}
