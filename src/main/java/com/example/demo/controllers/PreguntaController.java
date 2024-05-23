package com.example.demo.controllers;

import com.example.demo.models.PreguntaModel;
import com.example.demo.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/pregunta")
public class PreguntaController {
    @Autowired
    PreguntaService preguntaService;

    @GetMapping()
    public ArrayList<PreguntaModel> obtenerPreguntas(){
        return preguntaService.obtenerPreguntas();
    }

    @PostMapping()
    public PreguntaModel guardarPregunta(@RequestBody PreguntaModel pregunta){
        System.out.println(pregunta);
        return this.preguntaService.guardarPregunta(pregunta);
    }

    @GetMapping( path = "/{idPregunta}")
    public Optional<PreguntaModel> obtenerPreguntaPorIdPregunta(@PathVariable("idPregunta") Long idPregunta) {
        return this.preguntaService.obtenerPorIdPregunta(idPregunta);
    }

    @GetMapping( "/idExamen")
    public ArrayList<PreguntaModel> obtenerPreguntaIdExamen(@RequestParam("idExamen") Long idExamen) {
        return this.preguntaService.obtenerPoridExamen(idExamen);
    }


    @DeleteMapping( path = "/{idPregunta}")
    public boolean eliminarPorId(@PathVariable("idPregunta") Long idPregunta){
        return this.preguntaService.eliminarPregunta(idPregunta);

    }

    /*@DeleteMapping( path = "/{idPregunta}")
    public void eliminarPorId(@PathVariable("idPregunta") Long idPregunta){
        this.preguntaService.eliminarPregunta(idPregunta);
    }*/
}