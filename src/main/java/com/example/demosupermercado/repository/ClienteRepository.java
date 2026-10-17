package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
