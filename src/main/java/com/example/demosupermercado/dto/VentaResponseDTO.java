package com.example.demosupermercado.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VentaResponseDTO {

    private Long id;

    private LocalDateTime fecha;
    
    private BigDecimal total;

    private Long clienteId;

    private List<DetalleVentaResponseDTO> detalles;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long id, LocalDateTime fecha, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    public VentaResponseDTO(
            Long id,
            LocalDateTime fecha,
            BigDecimal total,
            Long clienteId,
            List<DetalleVentaResponseDTO> detalles) {
        this(id, fecha, total);
        this.clienteId = clienteId;
        this.detalles = detalles;
    }
}
