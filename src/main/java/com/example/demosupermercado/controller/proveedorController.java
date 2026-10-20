package com.example.demosupermercado.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.dto.ProveedorRequestDTO;
import com.example.demosupermercado.dto.ProveedorResponseDTO;
import com.example.demosupermercado.service.proveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedores")
public class proveedorController {

    private final proveedorService proveedorService;

    public proveedorController(proveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> createProveedor(@Valid @RequestBody ProveedorRequestDTO request) {
        ProveedorResponseDTO response = proveedorService.createProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> getAllProveedores() {
        return ResponseEntity.ok(proveedorService.getAllProveedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> getProveedorById(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.getProveedorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> updateProveedor(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorRequestDTO request) {
        return ResponseEntity.ok(proveedorService.updateProveedor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
