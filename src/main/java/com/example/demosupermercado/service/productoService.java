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
import com.example.demosupermercado.repository.DetalleVentaRepository;
import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.ProveedorRepository;

@Service 
public class productoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public productoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
            ProveedorRepository proveedorRepository, DetalleVentaRepository detalleVentaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public ProductoResponseDTO createProducto(ProductoRequestDTO request) {
        Producto producto = new Producto();
        if (productoRepository.existsByCodigoDeBarras(request.getCodigoDeBarras())) {
            throw new ConflictoDeRecursoException("Ya existe un producto con ese código de barras");
        }
        aplicarRequest(producto, request);
        producto = productoRepository.save(producto);
        return convertirADto(producto);
    }

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

    public List<ProductoResponseDTO> getProductosPorCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new RecursoNoEncontradoException("Categoría no encontrada");
        }
        return convertirLista(productoRepository.findByCategoria_Id(categoriaId));
    }

    public List<ProductoResponseDTO> buscarProductosPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        return convertirLista(productoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    public List<ProductoResponseDTO> getProductosConStockBajo(Long limite) {
        if (limite == null || limite < 0) {
            throw new IllegalArgumentException("El límite de stock no puede ser negativo");
        }
        return convertirLista(productoRepository.findByStockLessThanEqual(limite));
    }

    public ProductoResponseDTO updateProducto(Long id, ProductoRequestDTO request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));
        if (productoRepository.existsByCodigoDeBarrasAndIdNot(request.getCodigoDeBarras(), id)) {
            throw new ConflictoDeRecursoException("Ya existe un producto con ese código de barras");
        }
        aplicarRequest(producto, request);
        return convertirADto(productoRepository.save(producto));
    }

    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));
        if (detalleVentaRepository.existsByProducto_Id(id)) {
            throw new ConflictoDeRecursoException("No se puede eliminar el producto porque aparece en ventas");
        }
        productoRepository.delete(producto);
    }

    private void aplicarRequest(Producto producto, ProductoRequestDTO request) {
        producto.setNombre(request.getNombre());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());
        producto.setCodigoDeBarras(request.getCodigoDeBarras());

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada"));
        producto.setCategoria(categoria);

        if (request.getProveedorId() == null) {
            producto.setProveedor(null);
        } else {
            Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Proveedor no encontrado"));
            producto.setProveedor(proveedor);
        }
    }

    private List<ProductoResponseDTO> convertirLista(List<Producto> productos) {
        List<ProductoResponseDTO> response = new ArrayList<>();
        for (Producto producto : productos) {
            response.add(convertirADto(producto));
        }
        return response;
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
