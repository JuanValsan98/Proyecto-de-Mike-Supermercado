package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
