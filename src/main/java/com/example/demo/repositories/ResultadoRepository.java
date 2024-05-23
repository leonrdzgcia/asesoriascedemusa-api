package com.example.demo.repositories;

import com.example.demo.models.ResultadoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResultadoRepository extends CrudRepository<ResultadoModel, Long> {
    public abstract ArrayList<ResultadoModel> findByMatricula(Long celular);

}