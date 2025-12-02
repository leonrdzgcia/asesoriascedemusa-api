package com.example.demo.repositories;

import com.example.demo.models.CatalogoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRepository extends CrudRepository<CatalogoModel, Long> {

}
