package com.example.demo.repositories;

import com.example.demo.models.ExamenModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends CrudRepository<ExamenModel, Long> {
    //public abstract ArrayList<UsuarioModel> findByCelular(Integer celular);

}