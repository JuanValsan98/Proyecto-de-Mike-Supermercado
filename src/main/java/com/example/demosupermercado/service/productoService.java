package com.example.demosupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.Entity.Categoria;
import com.example.demosupermercado.Entity.Producto;
import com.example.demosupermercado.Entity.Proveedor;
import com.example.demosupermercado.dto.ProductoRequestDTO;
import com.example.demosupermercado.dto.ProductoResponseDTO;

import com.example.demosupermercado.exception.ConflictoDeRecursoException;
import com.example.demosupermercado.exception.RecursoNoEncontradoException;
import com.example.demosupermercado.repository.CategoriaRepository;
import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.ProveedorRepository;

@Service 
public class productoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    public productoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public ProductoResponseDTO createProducto(ProductoRequestDTO request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());
        producto.setCodigoDeBarras(request.getCodigoDeBarras());

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada"));

        producto.setCategoria(categoria);
        if (request.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Proveedor no encontrado"));
            producto.setProveedor(proveedor);
        }
        if (productoRepository.existsByCodigoDeBarras(request.getCodigoDeBarras())) {
            throw new ConflictoDeRecursoException("Ya existe un producto con ese código de barras");
        }
        producto = productoRepository.save(producto);

        return convertirADto(producto);
    }

/*------------------------------------------------------------------------------------------------------------------------------------ */

    public List<ProductoResponseDTO> getAllProductos() {
        List<ProductoResponseDTO> response = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();

        for (Producto producto : productos) {
            response.add(convertirADto(producto));
        }
        return response;
    }

    public ProductoResponseDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));
        return convertirADto(producto);
    }

    private ProductoResponseDTO convertirADto(Producto producto) {
        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setCodigoDeBarras(producto.getCodigoDeBarras());
        response.setNombre(producto.getNombre());
        response.setStock(producto.getStock());
        response.setPrecio(producto.getPrecio());
        response.setCategoriaId(producto.getCategoria().getId());
        response.setCategoriaNombre(producto.getCategoria().getNombre());
        if (producto.getProveedor() != null) {
            response.setProveedorId(producto.getProveedor().getId());
            response.setProveedorNombre(producto.getProveedor().getNombre());
        }
        return response;
    }
}
