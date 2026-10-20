package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDocumentoAndIdNot(String documento, Long id);
    boolean existsByDocumento(String documento);
    java.util.Optional<Cliente> findByDocumento(String documento);
}
