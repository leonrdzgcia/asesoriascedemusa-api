package com.example.demo.services;

import com.example.demo.models.ExamenModel;
import com.example.demo.repositories.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExamenService {
    @Autowired
    ExamenRepository preguntaRepository;
    
    public ArrayList<ExamenModel> obtenerExamens(){
        return (ArrayList<ExamenModel>) preguntaRepository.findAll();
    }

    public ExamenModel guardarExamen(ExamenModel pregunta){
        return preguntaRepository.save(pregunta);
    }

    /*public Optional<ExamenModel> obtenerPorMatricula(Long matricula){
        return preguntaRepository.findById(matricula);
    }*/

    public Optional<ExamenModel> obtenerPorIdExamen(Long idExamen){
        return preguntaRepository.findById(idExamen);
    }


    /*public ArrayList<ExamenModel>  obtenerPorCelular(Integer celular) {
        return preguntaRepository.findByCelular(celular);
    }*/

    public boolean eliminarExamen(Long matricula) {
        try{
            preguntaRepository.deleteById(matricula);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}