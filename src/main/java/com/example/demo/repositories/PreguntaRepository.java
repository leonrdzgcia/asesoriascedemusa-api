package com.example.demo.repositories;

import com.example.demo.models.PreguntaModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PreguntaRepository extends CrudRepository<PreguntaModel, Long> {
    public abstract ArrayList<PreguntaModel> findByIdExamen(Long idExamen);

    @Query(value = "select * from u255965900_cedemusadb.preguntas where id_Examen = ?1 order by id", nativeQuery = true)
    public abstract ArrayList<PreguntaModel> searchByFullByIdExamenQNative(Long idExamen);

}