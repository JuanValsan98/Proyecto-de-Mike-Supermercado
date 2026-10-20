package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Venta;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByCliente_Id(Long clienteId);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    boolean existsByCliente_Id(Long clienteId);
}
