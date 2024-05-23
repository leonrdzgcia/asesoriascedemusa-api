package com.example.demo.controllers;

import com.example.demo.models.ExamenModel;
import com.example.demo.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/examen")
public class ExamenController {
    @Autowired
    ExamenService examenService;

    @GetMapping()
    public ArrayList<ExamenModel> obtenerExamens(){
        return examenService.obtenerExamens();
    }

    @PostMapping()
    public ExamenModel guardarExamen(@RequestBody ExamenModel examen){
        return this.examenService.guardarExamen(examen);
    }

    /*@GetMapping( path = "/{id}")
    public Optional<ExamenModel> obtenerExamenPorMatricula(@PathVariable("matricula") Long matricula) {
        return this.examenService.obtenerPorMatricula(matricula);
    }*/

    @GetMapping( path = "/{idExamen}")
    public Optional<ExamenModel> obtenerExamenIdExamen(@PathVariable("idExamen") Long idExamen) {
        return this.examenService.obtenerPorIdExamen(idExamen);
    }

    /*@GetMapping("/query")
    public ArrayList<ExamenModel> obtenerExamenPorCelular(@RequestParam("celular") Integer celular){
        return this.examenService.obtenerPorCelular(celular);
    }*/

    @DeleteMapping( path = "/{matricula}")
    public String eliminarPorId(@PathVariable("matricula") Long matricula){
        boolean ok = this.examenService.eliminarExamen(matricula);
        if (ok){
            return "Se eliminó el examen con id " + matricula;
        }else{
            return "No pudo eliminar el examen con id" + matricula;
        }
    }
}