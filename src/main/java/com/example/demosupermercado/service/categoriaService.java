package com.example.demosupermercado.service;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.dto.CategoriaRequestDTO;
import com.example.demosupermercado.dto.CategoriaResponseDTO;
import com.example.demosupermercado.repository.CategoriaRepository;

@Service 
public class categoriaService {
    
    private final CategoriaRepository categoriaRepository;

     public categoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
     }

     public CategoriaResponseDTO createCategoria(CategoriaRequestDTO request) {
        

        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
       

        categoriaRepository.save(categoria);

        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
       
        return response;
    }
}
