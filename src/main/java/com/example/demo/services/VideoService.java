package com.example.demo.services;

import com.example.demo.controllers.VideoModel;
import com.example.demo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;

    public ArrayList<VideoModel> obtenerVideos(){
        return (ArrayList<VideoModel>) videoRepository.findAll();
    }

    public List<VideoModel> buscarVideos(){
        return videoRepository.buscarVideos();
    }

    public Optional<VideoModel> obtenerVideoPorId(Long id){
        return videoRepository.findById(id);
    }

    public VideoModel guardarVideo(VideoModel video){
        return videoRepository.save(video);
    }
}
