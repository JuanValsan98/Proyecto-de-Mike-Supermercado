package com.example.demosupermercado.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;

@Data 
@Entity 

public class Proveedor {

    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "Id")
    @Id 
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Correo")
    private String correo;

    @OneToMany(mappedBy = "proveedor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Producto> productos = new ArrayList<>();

    public Proveedor(){

    }

    public Proveedor(Long id, String nombre, String telefono, String correo){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

}