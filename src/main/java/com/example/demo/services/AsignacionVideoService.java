package com.example.demo.services;

import com.example.demo.models.AsignacionVideoModel;
import com.example.demo.repositories.AsignacionVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignacionVideoService {

    @Autowired
    AsignacionVideoRepository asignacionVideoRepository;

    public ArrayList<AsignacionVideoModel> obtenerAsignaciones() {
        return (ArrayList<AsignacionVideoModel>) asignacionVideoRepository.findAll();
    }

    public AsignacionVideoModel guardarAsignacion(AsignacionVideoModel asignacion) {
        return asignacionVideoRepository.save(asignacion);
    }

    public List<AsignacionVideoModel> buscarPorMatricula(String matricula) {
        return asignacionVideoRepository.buscarPorMatricula(matricula);
    }
}
