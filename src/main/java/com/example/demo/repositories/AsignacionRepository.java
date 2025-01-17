package com.example.demo.repositories;

import com.example.demo.controllers.VideoModel;
import com.example.demo.models.AsignacionModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AsignacionRepository extends CrudRepository<AsignacionModel, Long> {
    public abstract ArrayList<AsignacionModel> findByMatricula(Long celular);

    //List<AsignacionModel> findByMatriculad(String matricula);
    @Query(value ="SELECT * FROM u255965900_cedemusadb.asignaciones u where u.matricula = ?1 and u.id_examen != 0",nativeQuery = true)
    List<AsignacionModel> buscarMatricula(String matricula);

    @Query(value ="SELECT * FROM u255965900_cedemusadb.videos v order by v.id",nativeQuery = true)
    List<VideoModel> buscarVideos();

    @Query(value ="SELECT * FROM u255965900_cedemusadb.asignaciones u where u.matricula = ?1 and u.id_examen =?2 order by id_video",nativeQuery = true)
    List<AsignacionModel> asignacionVideoPorMatricula(String matricula, int id_examen);

    @Modifying
    @Transactional
    @Query(value ="DELETE FROM u255965900_cedemusadb.asignaciones where id_examen =0 and nombre_examen = :nombreVideo",nativeQuery = true)
    void eliminarAsignacionVideo(String nombreVideo);

}