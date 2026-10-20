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
    @Column(name = "Id")
    private Long id;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio_Unitario")
    private BigDecimal precioUnitario;

    @Column(name = "Sub_Total")
    private BigDecimal subtotal;

    @ManyToOne 
    @JoinColumn(name = "Venta_Id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "Producto_id")
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
