package com.example.demosupermercado.dto;



import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DetalleVentaResponseDTO {

    private Long id;

    @jakarta.validation.constraints.NotNull 
    @Positive 
    private Long cantidad;

    private BigDecimal precioUnitario;
    
    private BigDecimal subtotal;

    private Long productoId;

    private String productoNombre;

    public DetalleVentaResponseDTO() {
    }

    public DetalleVentaResponseDTO(Long id, Long cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public DetalleVentaResponseDTO(
            Long id,
            Long cantidad,
            BigDecimal precioUnitario,
            BigDecimal subtotal,
            Long productoId,
            String productoNombre) {
        this(id, cantidad, precioUnitario, subtotal);
        this.productoId = productoId;
        this.productoNombre = productoNombre;
    }
}
