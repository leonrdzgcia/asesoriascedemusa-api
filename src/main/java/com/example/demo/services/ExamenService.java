package com.example.demo.services;

import com.example.demo.models.ExamenModel;
import com.example.demo.models.UsuariosModel;
import com.example.demo.repositories.ExamenJpaRepository;
import com.example.demo.repositories.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {
    @Autowired
    ExamenRepository preguntaRepository;

    @Autowired
    ExamenJpaRepository preguntaJpaRepository;

    public ArrayList<ExamenModel> obtenerExamens(){
        return (ArrayList<ExamenModel>) preguntaRepository.findAll();
    }

    public ExamenModel guardarExamen(ExamenModel pregunta){
        return preguntaRepository.save(pregunta);
    }

    public Optional<ExamenModel> obtenerPorIdExamen(Long idExamen){
        return preguntaRepository.findById(idExamen);
    }


    public boolean eliminarExamen(Long matricula) {
        try{
            preguntaRepository.deleteById(matricula);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    //JPA CONSLTAS NATIVAS

    public List<ExamenModel> buscarExamenNivel(String nivel){
        return preguntaJpaRepository.buscarExamenNivel(nivel);
    }




}