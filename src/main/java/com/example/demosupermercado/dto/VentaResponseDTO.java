package com.example.demosupermercado.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class VentaResponseDTO {

    private Long id;

    private Date fecha;
    
    private double total;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long id, Date fecha, double total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }
}
