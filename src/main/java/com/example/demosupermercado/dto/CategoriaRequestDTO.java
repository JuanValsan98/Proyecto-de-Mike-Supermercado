package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class CategoriaRequestDTO {

    private String nombre;
    
    private String descripcion;

    public CategoriaRequestDTO() {
    }

    public CategoriaRequestDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
