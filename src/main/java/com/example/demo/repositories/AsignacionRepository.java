package com.example.demo.repositories;

import com.example.demo.models.AsignacionModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AsignacionRepository extends CrudRepository<AsignacionModel, Long> {
    public abstract ArrayList<AsignacionModel> findByMatricula(Long celular);

    //List<AsignacionModel> findByMatriculad(String matricula);

    @Query(value ="SELECT * FROM u255965900_cedemusadb.asignaciones u where u.matricula = ?1 and u.id_examen != 0",nativeQuery = true)
    List<AsignacionModel> buscarMatricula(String matricula);

    @Query(value ="SELECT * FROM u255965900_cedemusadb.asignaciones u where u.matricula = ?1 and u.id_examen =?2",nativeQuery = true)
    List<AsignacionModel> asignacionVideoPorMatricula(String matricula, int id_examen);

}