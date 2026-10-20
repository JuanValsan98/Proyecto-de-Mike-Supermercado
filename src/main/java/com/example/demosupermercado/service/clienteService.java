package com.example.demosupermercado.service;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Cliente;
import com.example.demosupermercado.dto.ClienteRequestDTO;
import com.example.demosupermercado.dto.ClienteResponseDTO;
import com.example.demosupermercado.repository.ClienteRepository;

@Service 
public class clienteService {
    private final ClienteRepository clienteRepository;

    public clienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO createCliente (ClienteRequestDTO request) {

        Cliente cliente = new Cliente();
        cliente.setDocumento(request.getDocumento());
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());

        cliente = clienteRepository.save(cliente);

        ClienteResponseDTO clienteResponseDTO = new  ClienteResponseDTO();
        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setDocumento(cliente.getDocumento());
        clienteResponseDTO.setNombre(cliente.getNombre());
        clienteResponseDTO.setTelefono(cliente.getTelefono());

        return clienteResponseDTO;
    }
    
}
