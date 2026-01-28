package com.example.demo.repositories;

import com.example.demo.models.AsignacionVideoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionVideoRepository extends CrudRepository<AsignacionVideoModel, Long> {

    @Query(value = "SELECT * FROM asignaciones_videos WHERE matricula = :matricula", nativeQuery = true)
    List<AsignacionVideoModel> buscarPorMatricula(@Param("matricula") String matricula);
}
