package com.example.demosupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    private Long codigoDeBarras;

    private String nombre;

    private Long stock;

    private double precio;

    private Long categoriaId;
}
