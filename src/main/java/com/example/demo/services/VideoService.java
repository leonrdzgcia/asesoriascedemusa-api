package com.example.demo.services;

import com.example.demo.controllers.VideoModel;
import com.example.demo.models.AsignacionModel;
import com.example.demo.repositories.AsignacionRepository;
import com.example.demo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
