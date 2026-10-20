package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.Entity.Producto;
import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;

import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.CategoriaRepository;

import lombok.Data;


@Data
@Service 
public class productoService {
     private final ProductoRepository ProductoRepository; 
     private final CategoriaRepository CategoriaRepository; 

    public productoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.ProductoRepository = productoRepository;
        this.CategoriaRepository = categoriaRepository;
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
        ProductoRepository.save(producto);

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setCodigoDeBarras(producto.getCodigoDeBarras());
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
        return response;
    }
    
    
    
}
