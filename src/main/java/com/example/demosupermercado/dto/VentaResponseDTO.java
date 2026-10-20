package com.example.demosupermercado.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VentaResponseDTO {

    private Long id;

    private LocalDateTime fecha;
    
    private BigDecimal total;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long id, LocalDateTime fecha, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }
}
