package com.example.demo.controllers;

import com.example.demo.models.AsignacionModel;
import com.example.demo.models.ResultadoModel;
import com.example.demo.services.AsignacionService;
import com.example.demo.services.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/resultado")
public class ResultadoController {
    @Autowired
    ResultadoService resultadoService;

    @GetMapping()
    public ArrayList<ResultadoModel> obtenerAsignacionesExamens(){
        return resultadoService.obtenerResultado();
    }

    @PostMapping()
    public ResultadoModel guardarAsignacion(@RequestBody ResultadoModel resultado){
        return this.resultadoService.guardarResultado(resultado);
    }

    @GetMapping( path = "/{matricula}")
    public ArrayList<ResultadoModel> obtenerAsignacionesMatricula(@PathVariable("matricula") Long matricula) {
        return this.resultadoService.obtenerPorMatricula(matricula);
    }

    @GetMapping("/examen")
    public List<ResultadoModel> obtenerExamen(@RequestParam("examen") String examen) {
        System.out.println(resultadoService.buscarResExamen(examen).get(0).getMatricula());
        return this.resultadoService.buscarResExamen(examen);
    }
}