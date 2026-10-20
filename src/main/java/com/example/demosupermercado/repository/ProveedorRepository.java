package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
