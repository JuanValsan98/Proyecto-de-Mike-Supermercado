package com.example.demosupermercado.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data 
public class Producto {

    @Column(name = "Codigo de barras", unique = true)
    private Long CodigoDeBarras;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Stock")
    private Long stock;

    @Column(name = "Precio")
    private BigDecimal precio;

    @ManyToOne 
    @JoinColumn(name = "Categoria Id")
    private Categoria categoria;

    public Producto() {
    }

    public Producto(Long codigoDeBarras, Long id, String nombre, Long stock, BigDecimal precio, Categoria categoria) {
        this.CodigoDeBarras = codigoDeBarras;
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
    }

}
