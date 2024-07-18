package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "resultados")
public class ResultadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String idExamen;
    private String matricula;
    private String correctas;
    private String incorrectas;
    private String totalPreguntas;
    private String calificacion;
    private String preguntasIncorrectas;
    private String preguntasCorrectas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(String idExamen) {
        this.idExamen = idExamen;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorrectas() {
        return correctas;
    }

    public void setCorrectas(String correctas) {
        this.correctas = correctas;
    }

    public String getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(String incorrectas) {
        this.incorrectas = incorrectas;
    }

    public String getTotalPreguntas() {
        return totalPreguntas;
    }

    public void setTotalPreguntas(String totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getPreguntasIncorrectas() {
        return preguntasIncorrectas;
    }

    public void setPreguntasIncorrectas(String preguntasIncorrectas) {
        this.preguntasIncorrectas = preguntasIncorrectas;
    }

    public String getPreguntasCorrectas() {
        return preguntasCorrectas;
    }
    public void setPreguntasCorrectas(String preguntasCorrectas) {
        this.preguntasCorrectas = preguntasCorrectas;
    }
}