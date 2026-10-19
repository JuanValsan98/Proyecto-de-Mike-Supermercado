package com.example.demosupermercado.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class VentaRequestDTO {

    private Date fecha;
    
    private double total;

    public VentaRequestDTO() {
    }

    public VentaRequestDTO(Date fecha, double total) {
        this.fecha = fecha;
        this.total = total;
    }
}
