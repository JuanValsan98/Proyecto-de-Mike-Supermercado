package com.example.demosupermercado.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data 

public class ProveedorRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    @Pattern(regexp = "[0-9+() -]{7,20}")
    private String telefono;

    @NotBlank
    @Email
    private String correo;

    public ProveedorRequestDTO() {
    }

    public ProveedorRequestDTO(String nombre, String telefono, String correo){

    this.nombre = nombre;
    this.telefono = telefono;
    this.correo = correo;
    }
    
}
