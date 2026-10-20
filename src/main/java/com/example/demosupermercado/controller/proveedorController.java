package com.example.demosupermercado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosupermercado.dto.ClienteRequestDTO;
import com.example.demosupermercado.dto.ClienteResponseDTO;
import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;
import com.example.demosupermercado.dto.ProveedorRequestDTO;
import com.example.demosupermercado.dto.ProveedorResponseDTO;
import com.example.demosupermercado.service.clienteService;
import com.example.demosupermercado.service.proveedorService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/api/proveedores")
public class proveedorController {

    public final proveedorService proveedorService;

    public proveedorController( proveedorService proveedorService){
        this.proveedorService = proveedorService;
    }

    @PostMapping("/crearProveedor")
    public ResponseEntity<ProveedorResponseDTO> createProveedor(@Valid @RequestBody ProveedorRequestDTO request) {
         ProveedorResponseDTO response = proveedorService.createProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    
}
