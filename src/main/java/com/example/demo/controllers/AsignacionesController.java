package com.example.demo.controllers;

import com.example.demo.models.AsignacionModel;
import com.example.demo.models.ExamenModel;
import com.example.demo.models.UsuariosModel;
import com.example.demo.services.AsignacionService;
import com.example.demo.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/asignaciones")
public class AsignacionesController {
    @Autowired
    AsignacionService asignacionService;

    @GetMapping()
    public ArrayList<AsignacionModel> obtenerAsignacionesExamens(){
        return asignacionService.obtenerAsignaciones();
    }

    @PostMapping()
    public AsignacionModel guardarAsignacion(@RequestBody AsignacionModel asignacion){
        return this.asignacionService.guardarAsignacion(asignacion);
    }

    //obtiene usuarios en base a la matricula
    @GetMapping("/matricula")
    public List<AsignacionModel> obtenerUsuarioPorMatricula(@RequestParam("matricula") String matricula) {
        System.out.println(asignacionService.buscarAsigMatricula(matricula).get(0).getMatricula());
        return this.asignacionService.buscarAsigMatricula(matricula);
    }

    @GetMapping("/matriculaV")
    public List<AsignacionModel> obtenerVideoPorMatricula(@RequestParam("matricula") String matricula) {
        //System.out.println(asignacionService.buscarAsignacionVideoMatricula(matricula,0).get(0).getMatricula());
        return this.asignacionService.buscarAsignacionVideoMatricula(matricula);
    }

    //obtiene usuarios en base a la matricula
    @GetMapping("/idExamen")
    public List<AsignacionModel> obtenerAsignacionExamen(@RequestParam("matricula") String matricula) {
        System.out.println(asignacionService.buscarAsigMatricula(matricula).get(0).getMatricula());
        return this.asignacionService.buscarAsigMatricula(matricula);
    }
}