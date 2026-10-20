package com.example.demosupermercado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demosupermercado.service.clienteService;

import jakarta.validation.Valid;

import com.example.demosupermercado.dto.ClienteRequestDTO;
import com.example.demosupermercado.dto.ClienteResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;


@Controller 
@RestController 
@RequestMapping("/api/clientes")
public class clienteController {

    public final clienteService clienteService;

    public clienteController( clienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping("crearCliente")
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO request) {
        ClienteResponseDTO response = clienteService.createCliente(request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    
}
