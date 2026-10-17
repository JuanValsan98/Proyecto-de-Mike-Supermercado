package com.example.demosupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaResponseDTO {

    private Long id;

    private Long cantidad;

    private double precioUnitario;
    
    private double subtotal;
}
