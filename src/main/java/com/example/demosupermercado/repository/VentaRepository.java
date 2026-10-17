package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
