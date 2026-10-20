package com.example.demosupermercado.dto;

import lombok.Data;

@Data 

public class ProveedorResponseDTO {

    private Long id;

    private String nombre;

    private String telefono;

    private String correo;

    public ProveedorResponseDTO(Long id, String nombre, String telefono, String correo){

    this.id = id;    
    this.nombre = nombre;
    this.telefono = telefono;
    this.correo = correo;
    }
    
}
    
