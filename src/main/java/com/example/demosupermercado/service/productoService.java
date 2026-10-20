package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.Entity.Producto;
import com.example.demosupermercado.Entity.Proveedor;
import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;

import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.CategoriaRepository;
import com.example.demosupermercado.repository.ProveedorRepository;

import lombok.Data;


@Data
@Service 
public class productoService {
     private final ProductoRepository ProductoRepository; 
     private final CategoriaRepository CategoriaRepository; 
     private final ProveedorRepository proveedorRepository;

    public productoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProveedorRepository proveedorRepository) {
        this.ProductoRepository = productoRepository;
        this.CategoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public ProductoResponseDTO createProducto (ProductoRequestDTO request) {
    
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());
        producto.setCodigoDeBarras(request.getCodigoDeBarras());

        Categoria Categoria = CategoriaRepository.findById(request.getCategoriaId())
    .orElseThrow(() -> new IllegalArgumentException("No existe esa categoría"));

        producto.setCategoria(Categoria);
        if (request.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                    .orElseThrow(() -> new IllegalArgumentException("No existe ese proveedor"));
            producto.setProveedor(proveedor);
        }
        ProductoRepository.save(producto);

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setCodigoDeBarras(producto.getCodigoDeBarras());
        if (producto.getProveedor() != null) {
            response.setProveedorId(producto.getProveedor().getId());
            response.setProveedorNombre(producto.getProveedor().getNombre());
        }
        return response;
    }

/*------------------------------------------------------------------------------------------------------------------------------------ */

    public List<ProductoResponseDTO>  getAllProductos () {
        List<ProductoResponseDTO> response = new ArrayList<>();
        List<Producto> usersFound = ProductoRepository.findAll();

        for (Producto productoFound : usersFound) {
            ProductoResponseDTO producto = new ProductoResponseDTO();
            producto.setId(productoFound.getId());
            producto.setNombre(productoFound.getNombre());
            producto.setCodigoDeBarras(productoFound.getCodigoDeBarras());
            producto.setPrecio(productoFound.getPrecio());
            producto.setCategoriaNombre(productoFound.getCategoria().getNombre());
            if (productoFound.getProveedor() != null) {
                producto.setProveedorId(productoFound.getProveedor().getId());
                producto.setProveedorNombre(productoFound.getProveedor().getNombre());
            }
            
            
            response.add(producto);
        }
        return response;


    }

/*------------------------------------------------------------------------------------------------------------------------------------ */  

    public ProductoResponseDTO getProductoById(Long id) {
    Producto producto = ProductoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setCodigoDeBarras(producto.getCodigoDeBarras());
        response.setCategoriaNombre(producto.getCategoria().getNombre());
        if (producto.getProveedor() != null) {
            response.setProveedorId(producto.getProveedor().getId());
            response.setProveedorNombre(producto.getProveedor().getNombre());
        }
        return response;
    }
    
    
    
}
