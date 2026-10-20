package com.example.demosupermercado.dto;



import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DetalleVentaRequestDTO {

    @jakarta.validation.constraints.NotNull 
    @Positive 
    private Long cantidad;

    private double precioUnitario;
    
    private double subtotal;

    private Long productoId;

    public DetalleVentaRequestDTO() {
    }

    public DetalleVentaRequestDTO(Long cantidad, double precioUnitario, double subtotal) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
}
