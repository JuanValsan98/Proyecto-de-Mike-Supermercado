package com.example.demosupermercado.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VentaRequestDTO {

    @jakarta.validation.constraints.NotNull 
    @jakarta.validation.constraints.Positive
    private Long clienteId;

    @jakarta.validation.constraints.NotNull 
    @Valid 
    @NotEmpty 
    private List<DetalleVentaRequestDTO> detalles;

    public VentaRequestDTO() {
    }

}
