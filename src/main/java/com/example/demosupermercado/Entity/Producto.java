package com.example.demosupermercado.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;

@Entity
@Data 
public class Producto {

    @Column(name = "codigo_de_barras", unique = true)
    private String codigoDeBarras;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @ManyToOne 
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne 
    @JoinColumn(name = "proveedor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Proveedor proveedor;

    public Producto() {
    }

    public Producto(String codigoDeBarras, Long id, String nombre, Long stock, BigDecimal precio, Categoria categoria) {
        this.codigoDeBarras = codigoDeBarras;
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
    }

}
