package com.example.demo.repositories;

import com.example.demo.models.AsignacionModel;
import com.example.demo.models.ResultadoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ResultadoRepository extends CrudRepository<ResultadoModel, Long> {
    public abstract ArrayList<ResultadoModel> findByMatricula(Long celular);

    @Query(value ="SELECT * FROM u255965900_cedemusadb.resultados u where u.id_examen = ?1",nativeQuery = true)
    List<ResultadoModel> buscarPorExamen(String examen);
    @Query(value ="SELECT * FROM u255965900_cedemusadb.resultados u where u.matricula = ?1",nativeQuery = true)
    List<ResultadoModel> buscarPorMatricula(String matricula);


}