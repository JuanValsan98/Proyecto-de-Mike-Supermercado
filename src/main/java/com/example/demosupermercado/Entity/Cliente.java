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

@Entity 
@Data 
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "documento", unique = true)
    private String documento;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Venta> ventas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String documento, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

}
