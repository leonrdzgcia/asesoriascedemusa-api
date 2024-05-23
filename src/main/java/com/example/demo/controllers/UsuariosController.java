package com.example.demo.controllers;

import com.example.demo.models.UsuariosModel;
import com.example.demo.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    UsuariosService usuariosService;

    //REGRESA todos los usuarios
    @GetMapping()
    public ArrayList<UsuariosModel> obtenerUsuarios(){
        System.out.println(usuariosService.obtenerUsuarios().size());
        System.out.println(usuariosService.obtenerUsuarios().get(0).getMatricula());
        System.out.println(usuariosService.obtenerUsuarios().get(0).getNombre());
        return usuariosService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuariosModel guardarUsuario(@RequestBody UsuariosModel usuario){
        return this.usuariosService.guardarUsuario(usuario);
    }

    // elimina el usuario en base al id
    @DeleteMapping( path = "/{idUsuario}")
    public String eliminarPorId(@PathVariable("idUsuario") Long idUsuario){
        boolean ok = this.usuariosService.eliminarUsuarios(idUsuario);
        if (ok){
            return "SSe eliminó el usuario con id " + idUsuario;
        }else{
            return "NNo pudo eliminar el usuario con id" + idUsuario;
        }
    }

    //obtiene usuarios en base a la matricula
    @GetMapping("/matricula")
    public List<UsuariosModel> obtenerUsuarioPorMatricula(@RequestParam("matricula") String matricula) {
        System.out.println(usuariosService.buscarUsuarioMatricula(matricula).get(0).getMatricula());
        return this.usuariosService.buscarUsuarioMatricula(matricula);
    }

    //obtiene usuarios que concidan en base a la nombre
    @GetMapping("/nombre")
    public List<UsuariosModel> obtenerUsuarioNombre(@RequestParam("nombre") String nombre){
        System.out.println(usuariosService.buscarUsuarioNombre(nombre).get(0).getNombre());
        return this.usuariosService.buscarUsuarioNombre(nombre);
    }

    @GetMapping("/idUsuario")
    public List<UsuariosModel> obtenerUsuarioId(@RequestParam("idUsuario") String idUsuario){
        System.out.println(usuariosService.buscarUsuarioId(idUsuario).get(0).getNombre());
        return this.usuariosService.buscarUsuarioId(idUsuario);
    }
}