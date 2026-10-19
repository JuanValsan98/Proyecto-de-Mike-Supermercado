package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;

    private String nombre;

    private Long documento;
    
    private int telefono;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nombre, Long documento, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }
}
