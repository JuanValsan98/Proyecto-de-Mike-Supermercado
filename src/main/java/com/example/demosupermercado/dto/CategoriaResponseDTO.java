package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class CategoriaResponseDTO {

    private Long id;

    private String nombre;
    
    private String descripcion;

    public CategoriaResponseDTO() {

    }

    public CategoriaResponseDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
