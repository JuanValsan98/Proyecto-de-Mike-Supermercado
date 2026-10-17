package com.example.demosupermercado.service;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.Entity.Producto;
import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;
import com.example.demosupermercado.dto.CategoriaRequestDTO;
import com.example.demosupermercado.dto.CategoriaResponseDTO;

import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.CategoriaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor 

@Data 

@Service 


public class productoService {
     private final ProductoRepository ProductoRepository; 
     private final CategoriaRepository CategoriaRepository; 

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
    
}
