package com.example.demo.controllers;

import com.example.demo.models.AsignacionVideoModel;
import com.example.demo.services.AsignacionVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asignacionesvideos")
public class AsignacionVideoController {

    @Autowired
    AsignacionVideoService asignacionVideoService;

    @GetMapping()
    public ArrayList<AsignacionVideoModel> obtenerAsignaciones() {
        return asignacionVideoService.obtenerAsignaciones();
    }

    @GetMapping("/matricula")
    public List<AsignacionVideoModel> obtenerPorMatricula(@RequestParam("matricula") String matricula) {
        return asignacionVideoService.buscarPorMatricula(matricula);
    }

    @PostMapping()
    public AsignacionVideoModel guardarAsignacion(@RequestBody AsignacionVideoModel asignacion) {
        return asignacionVideoService.guardarAsignacion(asignacion);
    }
}
