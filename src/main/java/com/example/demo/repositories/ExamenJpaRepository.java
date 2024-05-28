package com.example.demo.repositories;

import com.example.demo.models.ExamenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExamenJpaRepository extends JpaRepository<ExamenModel, Long> {

    @Query(value ="SELECT * FROM springdb.examen u where u.nivel = ?1",nativeQuery = true)
    List<ExamenModel> buscarExamenNivel(String nivel);
}