package com.example.demosupermercado.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.dto.VentaRequestDTO;
import com.example.demosupermercado.dto.VentaResponseDTO;
import com.example.demosupermercado.service.ventaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
public class ventaController {

    private final ventaService ventaService;

    public ventaController(ventaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> createVenta(@Valid @RequestBody VentaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.createVenta(request));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> getAllVentas() {
        return ResponseEntity.ok(ventaService.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getVentaById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getVentaById(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaResponseDTO>> getVentasPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ventaService.getVentasPorCliente(clienteId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<VentaResponseDTO>> getVentasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(ventaService.getVentasPorFecha(inicio, fin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }

}
