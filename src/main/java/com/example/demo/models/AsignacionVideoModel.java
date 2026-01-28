package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "asignaciones_videos")
public class AsignacionVideoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion", unique = true, nullable = false)
    private Long idAsignacion;

    @Column(name = "matricula", length = 45)
    private String matricula;

    @Column(name = "nombre_video", length = 255)
    private String nombreVideo;

    @Column(name = "clase", length = 255)
    private String clase;

    @Column(name = "id_video")
    private Integer idVideo;

    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreVideo() {
        return nombreVideo;
    }

    public void setNombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Integer getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Integer idVideo) {
        this.idVideo = idVideo;
    }
}
