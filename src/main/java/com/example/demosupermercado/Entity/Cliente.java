package com.example.demosupermercado.Entity;

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
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "documento", unique = true )
    private Long documento;

    @Column(name = "telefono")
    private int telefono;
    
}
