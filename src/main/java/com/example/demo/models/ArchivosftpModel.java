package com.example.demo.models;

public class ArchivosftpModel {
    private int id;
    private String nombreArchivo;

    public ArchivosftpModel(int id, String nombreArchivo ) {
        this.id= id;
        this.nombreArchivo = nombreArchivo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
