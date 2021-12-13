package com.informatorio.api.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity

public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = " El nombre no puede ser vacio")
    private String nombre;

    private String descripcion;

    public Long getId(){
        return id;
    }
    public Long getNombre(){
        return id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;

    }
    public String getDescripcion(){
        return descripcion;
    }
}
