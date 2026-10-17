package com.example.demosupermercado.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;
import com.example.demosupermercado.service.productoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class productoController {

    private final productoService productoService;

    

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProducto(@RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO response = productoService.createProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
