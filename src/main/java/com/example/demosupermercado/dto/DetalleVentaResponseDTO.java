package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class DetalleVentaResponseDTO {

    private Long id;

    private Long cantidad;

    private double precioUnitario;
    
    private double subtotal;

    public DetalleVentaResponseDTO() {
    }

    public DetalleVentaResponseDTO(Long id, Long cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
}
