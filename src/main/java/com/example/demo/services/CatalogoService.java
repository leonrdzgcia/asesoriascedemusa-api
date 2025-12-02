package com.example.demo.services;

import com.example.demo.models.CatalogoModel;
import com.example.demo.repositories.CatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CatalogoService {
    @Autowired
    CatalogoRepository catalogoRepository;

    public ArrayList<CatalogoModel> obtenerCatalogos() {
        return (ArrayList<CatalogoModel>) catalogoRepository.findAll();
    }

    public CatalogoModel guardarCatalogo(CatalogoModel catalogo) {
        return catalogoRepository.save(catalogo);
    }

    public Optional<CatalogoModel> obtenerPorId(Long id) {
        return catalogoRepository.findById(id);
    }

    public CatalogoModel actualizarCatalogo(Long id, CatalogoModel catalogoActualizado) {
        Optional<CatalogoModel> catalogoExistente = catalogoRepository.findById(id);
        if (catalogoExistente.isPresent()) {
            CatalogoModel catalogo = catalogoExistente.get();
            catalogo.setClase(catalogoActualizado.getClase());
            return catalogoRepository.save(catalogo);
        }
        return null;
    }

    public boolean eliminarCatalogo(Long id) {
        try {
            catalogoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
