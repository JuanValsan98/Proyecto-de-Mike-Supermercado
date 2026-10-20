package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.dto.CategoriaRequestDTO;
import com.example.demosupermercado.dto.CategoriaResponseDTO;
import com.example.demosupermercado.exception.ConflictoDeRecursoException;
import com.example.demosupermercado.exception.RecursoNoEncontradoException;
import com.example.demosupermercado.repository.CategoriaRepository;
import com.example.demosupermercado.repository.ProductoRepository;

@Service 
public class categoriaService {
    
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

     public categoriaService(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
     }

     public CategoriaResponseDTO createCategoria(CategoriaRequestDTO request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        return convertirADto(categoriaRepository.save(categoria));
     }

     public List<CategoriaResponseDTO> getAllCategorias() {
        List<CategoriaResponseDTO> response = new ArrayList<>();
        for (Categoria categoria : categoriaRepository.findAll()) {
            response.add(convertirADto(categoria));
        }
        return response;
     }

     public CategoriaResponseDTO getCategoriaById(Long id) {
        return convertirADto(buscarCategoria(id));
     }

     public CategoriaResponseDTO updateCategoria(Long id, CategoriaRequestDTO request) {
        Categoria categoria = buscarCategoria(id);
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        return convertirADto(categoriaRepository.save(categoria));
     }

     public void deleteCategoria(Long id) {
        Categoria categoria = buscarCategoria(id);
        if (productoRepository.existsByCategoria_Id(id)) {
            throw new ConflictoDeRecursoException("No se puede eliminar la categoría porque tiene productos asociados");
        }
        categoriaRepository.delete(categoria);
     }

     private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada"));
     }

     private CategoriaResponseDTO convertirADto(Categoria categoria) {
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
        return response;
    }
}
