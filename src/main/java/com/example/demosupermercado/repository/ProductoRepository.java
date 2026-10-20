package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByCodigoDeBarras(String codigoDeBarras);
}
