package com.example.demosupermercado.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity 
@AllArgsConstructor
@Data 
public class DetalleVenta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id 
    @Column(name = "Id")
    private Long id;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio Unitario")
    private double precioUnitario;

    @Column(name = "Sub Total")
    private double subtotal;
    
}
