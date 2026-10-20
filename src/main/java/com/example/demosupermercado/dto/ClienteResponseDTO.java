package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;

    private String nombre;

    private String documento;
    
    private String telefono;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nombre, String documento, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }
}
