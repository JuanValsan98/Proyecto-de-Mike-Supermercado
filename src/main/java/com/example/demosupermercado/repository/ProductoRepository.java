package com.example.demosupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosupermercado.Entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByCodigoDeBarras(String codigoDeBarras);
    boolean existsByCodigoDeBarrasAndIdNot(String codigoDeBarras, Long id);
    boolean existsByCategoria_Id(Long categoriaId);
    boolean existsByProveedor_Id(Long proveedorId);
    java.util.List<Producto> findByCategoria_Id(Long categoriaId);
    java.util.List<Producto> findByNombreContainingIgnoreCase(String nombre);
    java.util.List<Producto> findByStockLessThanEqual(Long limite);
}
