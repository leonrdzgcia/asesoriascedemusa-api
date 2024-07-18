package com.example.demo.services;

import com.example.demo.models.AsignacionModel;
import com.example.demo.models.ResultadoModel;
import com.example.demo.repositories.AsignacionRepository;
import com.example.demo.repositories.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultadoService {
    @Autowired
    ResultadoRepository resultadoRepository;
    
    public ArrayList<ResultadoModel> obtenerResultado(){
        return (ArrayList<ResultadoModel>) resultadoRepository.findAll();
    }

    public ResultadoModel guardarResultado(ResultadoModel resultado){
        return resultadoRepository.save(resultado);
    }


    public ArrayList<ResultadoModel> obtenerPorMatricula(Long matricula){
        return resultadoRepository.findByMatricula(matricula);
    }

    public List<ResultadoModel> buscarResExamen(String examen){
        return resultadoRepository.buscarPorExamen(examen);
    }

}