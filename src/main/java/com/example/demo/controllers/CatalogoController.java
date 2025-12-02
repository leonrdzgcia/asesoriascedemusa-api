package com.example.demo.controllers;

import com.example.demo.models.CatalogoModel;
import com.example.demo.services.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/catalogos")
public class CatalogoController {

    @Autowired
    CatalogoService catalogoService;

    @GetMapping()
    public ArrayList<CatalogoModel> obtenerCatalogos() {
        return catalogoService.obtenerCatalogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoModel> obtenerCatalogoPorId(@PathVariable Long id) {
        Optional<CatalogoModel> catalogo = catalogoService.obtenerPorId(id);
        return catalogo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CatalogoModel> guardarCatalogo(@RequestBody CatalogoModel catalogo) {
        try {
            CatalogoModel catalogoGuardado = catalogoService.guardarCatalogo(catalogo);
            return ResponseEntity.status(HttpStatus.CREATED).body(catalogoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoModel> actualizarCatalogo(
            @PathVariable Long id,
            @RequestBody CatalogoModel catalogo) {
        try {
            CatalogoModel catalogoActualizado = catalogoService.actualizarCatalogo(id, catalogo);
            if (catalogoActualizado != null) {
                return ResponseEntity.ok(catalogoActualizado);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCatalogo(@PathVariable Long id) {
        try {
            boolean eliminado = catalogoService.eliminarCatalogo(id);
            if (eliminado) {
                return ResponseEntity.ok("Catálogo eliminado exitosamente");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el catálogo");
        }
    }
}
