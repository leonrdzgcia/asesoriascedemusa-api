package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
public class PreguntaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idPregunta;
    private Long idExamen;
    private Long id;
    private String encabezado;
    private String pregunta;
    private String preguntaImagen;
    private String respuesta_1;
    private String respuesta_2;
    private String respuesta_3;
    private String correcta;
    private String tipoRespuestas;

    //private String imagen_1;private String imagen_2;    private String imagen_3;


    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Long getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Long idExamen) {
        this.idExamen = idExamen;
    }
    public Long getId() {        return id;    }

    public void setId(Long id) {        this.id = id;    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPreguntaImagen() {
        return preguntaImagen;
    }

    public void setPreguntaImagen(String preguntaImagen) {
        this.preguntaImagen = preguntaImagen;
    }

    public String getRespuesta_1() {
        return respuesta_1;
    }

    public void setRespuesta_1(String respuesta_1) {
        this.respuesta_1 = respuesta_1;
    }

    public String getRespuesta_2() {
        return respuesta_2;
    }

    public void setRespuesta_2(String respuesta_2) {
        this.respuesta_2 = respuesta_2;
    }

    public String getRespuesta_3() {
        return respuesta_3;
    }

    public void setRespuesta_3(String respuesta_3) {
        this.respuesta_3 = respuesta_3;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }
    public String getTipoRespuestas() {
        return tipoRespuestas;
    }

    public void setTipoRespuestas(String tipoRespuestas) {
        this.tipoRespuestas = tipoRespuestas;
    }
}