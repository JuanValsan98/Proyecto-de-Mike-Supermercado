package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Proveedor;
import com.example.demosupermercado.dto.ProveedorRequestDTO;
import com.example.demosupermercado.dto.ProveedorResponseDTO;
import com.example.demosupermercado.exception.ConflictoDeRecursoException;
import com.example.demosupermercado.exception.RecursoNoEncontradoException;
import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.ProveedorRepository;

@Service 
public class proveedorService {
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;

    public proveedorService(ProveedorRepository proveedorRepository, ProductoRepository productoRepository) {
        this.proveedorRepository = proveedorRepository;
        this.productoRepository = productoRepository;
    }

    public ProveedorResponseDTO createProveedor (ProveedorRequestDTO request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setCorreo(request.getCorreo());
        return convertirADto(proveedorRepository.save(proveedor));
    }

    public List<ProveedorResponseDTO> getAllProveedores() {
        List<ProveedorResponseDTO> response = new ArrayList<>();
        for (Proveedor proveedor : proveedorRepository.findAll()) {
            response.add(convertirADto(proveedor));
        }
        return response;
    }

    public ProveedorResponseDTO getProveedorById(Long id) {
        return convertirADto(buscarProveedor(id));
    }

    public ProveedorResponseDTO updateProveedor(Long id, ProveedorRequestDTO request) {
        Proveedor proveedor = buscarProveedor(id);
        proveedor.setNombre(request.getNombre());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setCorreo(request.getCorreo());
        return convertirADto(proveedorRepository.save(proveedor));
    }

    public void deleteProveedor(Long id) {
        Proveedor proveedor = buscarProveedor(id);
        if (productoRepository.existsByProveedor_Id(id)) {
            throw new ConflictoDeRecursoException("No se puede eliminar el proveedor porque tiene productos asociados");
        }
        proveedorRepository.delete(proveedor);
    }

    private Proveedor buscarProveedor(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proveedor no encontrado"));
    }

    private ProveedorResponseDTO convertirADto(Proveedor proveedor) {
        ProveedorResponseDTO response = new ProveedorResponseDTO();
        response.setId(proveedor.getId());
        response.setCorreo(proveedor.getCorreo());
        response.setNombre(proveedor.getNombre());
        response.setTelefono(proveedor.getTelefono());
        return response;
    }
}
