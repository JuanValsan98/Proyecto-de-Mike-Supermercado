package com.example.demosupermercado.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoResponseDTO {

    private Long id;

    private Long codigoDeBarras;

    private String nombre;

    private Long stock;
    
    private BigDecimal precio;

    private Long categoriaId;

    private String categoriaNombre;

    public ProductoResponseDTO() {
    }

    public ProductoResponseDTO(Long id, Long codigoDeBarras, String nombre, Long stock, BigDecimal precio, Long categoriaId, String categoriaNombre) {
        this.id = id;
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
    }
}
