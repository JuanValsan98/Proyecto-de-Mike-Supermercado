package com.example.demosupermercado.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data 
@Entity 

public class Proveedor {

    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Telefono")
    private Long telefono;

    @Column(name = "Correo")
    private String correo;

    @OneToMany 
    @JoinColumn(name = "Producto Id")
    private Producto producto;

    public Proveedor(){

    }

    public Proveedor(Long id, String nombre, Long telefono, String correo){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

}