package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Cliente;
import com.example.demosupermercado.dto.ClienteRequestDTO;
import com.example.demosupermercado.dto.ClienteResponseDTO;
import com.example.demosupermercado.exception.ConflictoDeRecursoException;
import com.example.demosupermercado.exception.RecursoNoEncontradoException;
import com.example.demosupermercado.repository.ClienteRepository;
import com.example.demosupermercado.repository.VentaRepository;

@Service 
public class clienteService {
    private final ClienteRepository clienteRepository;
    private final VentaRepository ventaRepository;

    public clienteService(ClienteRepository clienteRepository, VentaRepository ventaRepository) {
        this.clienteRepository = clienteRepository;
        this.ventaRepository = ventaRepository;
    }

    public ClienteResponseDTO createCliente (ClienteRequestDTO request) {
        Cliente cliente = new Cliente();
        cliente.setDocumento(request.getDocumento());
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());

        if (clienteRepository.existsByDocumento(request.getDocumento())) {
            throw new ConflictoDeRecursoException("Ya existe un cliente con ese documento");
        }
        return convertirADto(clienteRepository.save(cliente));
    }

    public List<ClienteResponseDTO> getAllClientes() {
        List<ClienteResponseDTO> response = new ArrayList<>();
        for (Cliente cliente : clienteRepository.findAll()) {
            response.add(convertirADto(cliente));
        }
        return response;
    }

    public ClienteResponseDTO getClienteById(Long id) {
        return convertirADto(buscarCliente(id));
    }

    public ClienteResponseDTO getClienteByDocumento(String documento) {
        Cliente cliente = clienteRepository.findByDocumento(documento)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
        return convertirADto(cliente);
    }

    public ClienteResponseDTO updateCliente(Long id, ClienteRequestDTO request) {
        Cliente cliente = buscarCliente(id);
        if (clienteRepository.existsByDocumentoAndIdNot(request.getDocumento(), id)) {
            throw new ConflictoDeRecursoException("Ya existe un cliente con ese documento");
        }
        cliente.setDocumento(request.getDocumento());
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        return convertirADto(clienteRepository.save(cliente));
    }

    public void deleteCliente(Long id) {
        Cliente cliente = buscarCliente(id);
        if (ventaRepository.existsByCliente_Id(id)) {
            throw new ConflictoDeRecursoException("No se puede eliminar el cliente porque tiene ventas asociadas");
        }
        clienteRepository.delete(cliente);
    }

    private Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
    }

    private ClienteResponseDTO convertirADto(Cliente cliente) {
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setDocumento(cliente.getDocumento());
        response.setNombre(cliente.getNombre());
        response.setTelefono(cliente.getTelefono());
        return response;
    }
}
