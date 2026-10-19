package com.example.demosupermercado.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;
import com.example.demosupermercado.service.productoService;

@RestController
@RequestMapping("/api/productos")
public class productoController {

    private final productoService productoService;

    public productoController(productoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProducto(@RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO response = productoService.createProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
