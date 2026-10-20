package com.example.demosupermercado.service;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Proveedor;
import com.example.demosupermercado.dto.ProveedorRequestDTO;
import com.example.demosupermercado.dto.ProveedorResponseDTO;
import com.example.demosupermercado.repository.ProveedorRepository;

@Service 
public class proveedorService {
    private final ProveedorRepository proveedorRepository;

    public proveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public ProveedorResponseDTO createProveedor (ProveedorRequestDTO request) {

        Proveedor proveedor = new Proveedor();
      
        proveedor.setNombre(request.getNombre());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setCorreo(request.getCorreo());

        proveedor = proveedorRepository.save(proveedor);

        ProveedorResponseDTO proveedorResponseDTO = new ProveedorResponseDTO();
        proveedorResponseDTO.setId(proveedor.getId());
        proveedorResponseDTO.setCorreo(proveedor.getCorreo());
        proveedorResponseDTO.setNombre(proveedor.getNombre());
        proveedorResponseDTO.setTelefono(proveedor.getTelefono());

        return proveedorResponseDTO;
    }
    
}
