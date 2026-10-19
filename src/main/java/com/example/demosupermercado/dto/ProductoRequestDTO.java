package com.example.demosupermercado.dto;

import lombok.Data;

@Data
public class ProductoRequestDTO {

    private Long codigoDeBarras;

    private String nombre;

    private Long stock;

    private double precio;

    private Long categoriaId;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(Long codigoDeBarras, String nombre, Long stock, double precio, Long categoriaId) {
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }
}
