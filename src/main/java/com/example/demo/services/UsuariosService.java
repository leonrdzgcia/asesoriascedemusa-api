package com.example.demo.services;

import com.example.demo.models.UsuariosModel;
import com.example.demo.repositories.UsuarioJpaRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    public UsuariosService(UsuarioRepository usuariosRepository) {
        this.usuarioRepository = usuariosRepository;
    }

    public ArrayList<UsuariosModel> obtenerUsuarios(){
        return (ArrayList<UsuariosModel>) usuarioRepository.findAll();
    }

    public UsuariosModel guardarUsuario(UsuariosModel usuario){
        return usuarioRepository.save(usuario);
    }



    public boolean eliminarUsuarios(Long idUsuario) {
        try{
            usuarioRepository.deleteById(idUsuario);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    //JPA CONSLTAS NATIVAS

    public List<UsuariosModel> buscarUsuarioMatricula(String matricula){
        return usuarioJpaRepository.buscarMatricula(matricula);
    }

    public List<UsuariosModel> buscarUsuarioNombre(String nombre) {
        return usuarioJpaRepository.buscarNombre(nombre);
    }
    public List<UsuariosModel> buscarUsuarioId(String idUsuario) {
        return usuarioJpaRepository.buscarID(idUsuario);
    }
}