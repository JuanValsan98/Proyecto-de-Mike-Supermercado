package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class DetalleVentaRequestDTO {

    private Long cantidad;

    private double precioUnitario;
    
    private double subtotal;

    public DetalleVentaRequestDTO() {
    }

    public DetalleVentaRequestDTO(Long cantidad, double precioUnitario, double subtotal) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
}
