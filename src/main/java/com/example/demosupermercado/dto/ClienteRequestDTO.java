package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nombre;

    private Long documento;
    
    private int telefono;

    public ClienteRequestDTO() {
    }

    public ClienteRequestDTO(String nombre, Long documento, int telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }
}
