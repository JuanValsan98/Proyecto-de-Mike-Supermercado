package com.example.demosupermercado.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DetalleVentaRequestDTO {

    @NotNull
    @Positive 
    private Long cantidad;

    @NotNull
    @Positive
    private Long productoId;

    public DetalleVentaRequestDTO() {
    }

    public DetalleVentaRequestDTO(Long cantidad, Long productoId) {
        this.cantidad = cantidad;
        this.productoId = productoId;
    }
}
