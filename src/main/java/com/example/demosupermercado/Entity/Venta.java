package com.example.demosupermercado.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@Data 
public class Venta {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Fecha")
    private Date fecha;

    @Column(name = "Total")
    private double total;

    public Venta() {
    }

    public Venta(Long id, Date fecha, double total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

}
