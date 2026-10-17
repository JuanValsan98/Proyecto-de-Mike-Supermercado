package com.example.demosupermercado.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private double precio;

    @ManyToOne 
    @JoinColumn(name = "Categoria Id")
    private Categoria categoria;

}
