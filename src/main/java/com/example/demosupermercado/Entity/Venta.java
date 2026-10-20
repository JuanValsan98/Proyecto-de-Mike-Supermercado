package com.example.demosupermercado.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;

@Entity 
@Data 
public class Venta {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Total")
    private BigDecimal total;

    @ManyToOne 
    @JoinColumn(name = "Cliente_Id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DetalleVenta> detalles = new ArrayList<>();

    public Venta() {
    }

    public Venta(Long id, LocalDateTime fecha, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        detalle.setVenta(this);
    }

}
