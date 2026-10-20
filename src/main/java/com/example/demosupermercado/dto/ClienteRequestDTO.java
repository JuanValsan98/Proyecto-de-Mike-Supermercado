package com.example.demosupermercado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String documento;
    
    @NotBlank
    @Pattern(regexp = "[0-9+() -]{7,20}")
    private String telefono;

    public ClienteRequestDTO() {
    }

    public ClienteRequestDTO(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }
}
