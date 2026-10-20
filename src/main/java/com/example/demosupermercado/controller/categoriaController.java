package com.example.demosupermercado.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.dto.CategoriaRequestDTO;
import com.example.demosupermercado.dto.CategoriaResponseDTO;
import com.example.demosupermercado.service.categoriaService;

@RestController
@RequestMapping("/api/categorias")
public class categoriaController {

    private final categoriaService categoriaService;

    public categoriaController(categoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@Valid @RequestBody CategoriaRequestDTO request) {
        CategoriaResponseDTO response = categoriaService.createCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
