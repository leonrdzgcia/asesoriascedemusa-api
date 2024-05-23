package com.example.demo.services;

import com.example.demo.models.PreguntaModel;
import com.example.demo.repositories.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PreguntaService {

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    PreguntaRepository preguntaRepository;
    
    public ArrayList<PreguntaModel> obtenerPreguntas(){
        return (ArrayList<PreguntaModel>) preguntaRepository.findAll();
    }

    public PreguntaModel guardarPregunta(PreguntaModel pregunta){
        return preguntaRepository.save(pregunta);
    }

    public Optional<PreguntaModel> obtenerPorIdPregunta(Long idPregunta){

        return preguntaRepository.findById(idPregunta);
    }

    /*public ArrayList<PreguntaModel> obtenerPoridExamen(Long idExamen){
        return preguntaRepository.findByIdExamen(idExamen);
    }*/

    public ArrayList<PreguntaModel> obtenerPoridExamen(Long idExamen){
        return preguntaRepository.searchByFullByIdExamenQNative(idExamen);
    }


    public ArrayList<PreguntaModel>  obtenerPorCelular(Long idExamen) {
        if(preguntaRepository.findByIdExamen(idExamen) == null){

            logger.info("Method...");  // Compliant, output via logger

            return preguntaRepository.findByIdExamen(idExamen);
        }else {

            logger.info("----NO ES NULL---");  // Compliant, output via logger

            return preguntaRepository.findByIdExamen(idExamen);
        }

    }

    public boolean eliminarPregunta(Long idPregunta) {
        try{
            preguntaRepository.deleteById(idPregunta);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    /*public void eliminarPregunta(Long idPregunta) {
            preguntaRepository.deleteById(idPregunta);
    }*/


    
}