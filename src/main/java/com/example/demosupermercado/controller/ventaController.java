package com.example.demosupermercado.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.service.ventaService;

import jakarta.validation.Valid;

import com.example.demosupermercado.dto.VentaRequestDTO;
import com.example.demosupermercado.dto.VentaResponseDTO;
@RequestMapping("/api/ventas")
public class ventaController {

    private final ventaService ventaService;
     public ventaController(ventaService ventaService){
        this.ventaService = ventaService;
     }

    @PostMapping
public ResponseEntity<VentaResponseDTO> createVenta(@Valid @RequestBody VentaRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.createVenta(request));
    
}

}
