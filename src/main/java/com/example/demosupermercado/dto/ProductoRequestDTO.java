package com.example.demosupermercado.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoRequestDTO {

    private Long codigoDeBarras;

    private String nombre;

    private Long stock;

    private BigDecimal precio;

    private Long categoriaId;

    private Long proveedorId;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(Long codigoDeBarras, String nombre, Long stock, BigDecimal precio, Long categoriaId, Long proveedorId) {
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.proveedorId = proveedorId;
    }
}
