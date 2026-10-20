package com.example.demosupermercado.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VentaRequestDTO {

    private LocalDateTime fecha;
    
    private BigDecimal total;

    @jakarta.validation.constraints.NotNull 
    private Long clienteId;

    @jakarta.validation.constraints.NotNull 
    @Valid 
    @NotEmpty 
    private List<DetalleVentaRequestDTO> detalles;

    public VentaRequestDTO() {
    }

    public VentaRequestDTO(LocalDateTime fecha, BigDecimal total) {
        this.fecha = fecha;
        this.total = total;
    }
}
