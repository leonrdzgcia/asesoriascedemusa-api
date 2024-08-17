package com.example.demo.services;

import com.example.demo.models.AsignacionModel;
import com.example.demo.models.ExamenModel;
import com.example.demo.repositories.AsignacionRepository;
import com.example.demo.repositories.ExamenRepository;
import jnr.posix.WString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignacionService {
    @Autowired
    AsignacionRepository asignacionRepository;
    
    public ArrayList<AsignacionModel> obtenerAsignaciones(){
        return (ArrayList<AsignacionModel>) asignacionRepository.findAll();
    }

    public AsignacionModel guardarAsignacion(AsignacionModel asignacion){
        return asignacionRepository.save(asignacion);
    }


    public List<AsignacionModel> buscarAsigMatricula(String matricula){
        return asignacionRepository.buscarMatricula(matricula);
    }

    public List<AsignacionModel> buscarAsignacionVideoMatricula(String matricula){
        return asignacionRepository.asignacionVideoPorMatricula(matricula,0);
    }



}