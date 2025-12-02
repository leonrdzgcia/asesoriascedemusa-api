package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "clase_videos")
public class CatalogoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase", unique = true, nullable = false)
    private Long id;

    @Column(name = "clase", nullable = false)
    private String clase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
