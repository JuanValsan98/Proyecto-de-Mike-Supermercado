package com.example.demosupermercado.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;

@Entity 
@Data 
public class DetalleVenta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id 
    @Column(name = "id")
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "sub_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne 
    @JoinColumn(name = "venta_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, Long cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

}
