package com.example.demo.repositories;

import com.example.demo.controllers.VideoModel;
import com.example.demo.models.AsignacionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoRepository extends CrudRepository<VideoModel, Long> {

    @Query(value ="SELECT * FROM u255965900_cedemusadb.videos v order by v.id",nativeQuery = true)
    List<VideoModel> buscarVideos();
}
