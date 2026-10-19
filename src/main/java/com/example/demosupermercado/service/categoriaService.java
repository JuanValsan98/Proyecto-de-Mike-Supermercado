package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.Entity.Producto;
import com.example.demosupermercado.dto.CategoriaRequestDTO;
import com.example.demosupermercado.dto.CategoriaResponseDTO;
import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;

import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.CategoriaRepository;

import lombok.Data;

@Data
@Service 


public class categoriaService {
    
    private final CategoriaRepository CategoriaRepository;
    private final ProductoRepository ProductoRepository;

     public CategoriaResponseDTO createCategoria (CategoriaRequestDTO request) {
        

        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
       

        CategoriaRepository.save(categoria);

        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
       
        return response;
    }
}
