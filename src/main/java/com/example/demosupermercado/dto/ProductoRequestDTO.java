package com.example.demosupermercado.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductoRequestDTO {

    @NotBlank
    private String codigoDeBarras;

    @NotBlank
    private String nombre;

    @NotNull
    @PositiveOrZero
    private Long stock;

    @NotNull
    @Positive
    private BigDecimal precio;

    @NotNull
    @Positive
    private Long categoriaId;

    @Positive
    private Long proveedorId;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(String codigoDeBarras, String nombre, Long stock, BigDecimal precio, Long categoriaId, Long proveedorId) {
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.proveedorId = proveedorId;
    }
}
