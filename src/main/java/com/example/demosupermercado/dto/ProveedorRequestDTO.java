package com.example.demosupermercado.dto;

import lombok.Data;

@Data 

public class ProveedorRequestDTO {

    private String nombre;

    private Long telefono;

    private String correo;

    public ProveedorRequestDTO(String nombre, Long telefono, String correo){

    this.nombre = nombre;
    this.telefono = telefono;
    this.correo = correo;
    }
    
}
