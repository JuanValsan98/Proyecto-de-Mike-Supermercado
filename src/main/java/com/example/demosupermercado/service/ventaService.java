package com.example.demosupermercado.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demosupermercado.dto.VentaRequestDTO;
import com.example.demosupermercado.dto.VentaResponseDTO;
import com.example.demosupermercado.repository.ClienteRepository;
import com.example.demosupermercado.repository.ProductoRepository;
import com.example.demosupermercado.repository.VentaRepository;

import jakarta.transaction.Transactional;

import com.example.demosupermercado.Entity.Venta;
import com.example.demosupermercado.Entity.Cliente;
import com.example.demosupermercado.Entity.DetalleVenta;
import com.example.demosupermercado.Entity.Producto;
import com.example.demosupermercado.dto.DetalleVentaRequestDTO;
import com.example.demosupermercado.exception.RecursoNoEncontradoException;
import com.example.demosupermercado.exception.StockInsuficienteException;
import com.example.demosupermercado.dto.DetalleVentaResponseDTO;

@Service 
public class ventaService {

    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final VentaRepository ventaRepository;


    public ventaService(ProductoRepository productoRepository,
        ClienteRepository clienteRepository,
        VentaRepository ventaRepository)
         {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.ventaRepository = ventaRepository;
    }


    @Transactional 
public VentaResponseDTO crearVenta(VentaRequestDTO request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
    .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now());
        BigDecimal total = BigDecimal.ZERO;

   
    for (DetalleVentaRequestDTO item : request.getDetalles()) {

        Producto producto = productoRepository.findById(item.getProductoId())
        .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));

        if (producto.getStock() < item.getCantidad()) {
            throw new StockInsuficienteException("Stock insuficiente para " + producto.getNombre());
        }
        DetalleVenta detalle = new DetalleVenta();
        detalle.setProducto(producto);
        detalle.setCantidad(item.getCantidad());
        detalle.setPrecioUnitario(producto.getPrecio());
        BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad()));
        detalle.setSubtotal(subtotal);
        

        producto.setStock(producto.getStock() - item.getCantidad());
        productoRepository.save(producto);
        
        detalle.setVenta(venta);             
        venta.getDetalles().add(detalle);
    

        total = total.add(subtotal);
    }

        venta.setTotal(total);
        Venta ventaGuardada = ventaRepository.save(venta);
        
        List<DetalleVentaResponseDTO> detallesResponse = ventaGuardada.getDetalles().stream()
                .map(detalle -> new DetalleVentaResponseDTO(
                        detalle.getId(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.getSubtotal(),
                        detalle.getProducto().getId(),
                        detalle.getProducto().getNombre()))
                .collect(Collectors.toList());

        return new VentaResponseDTO(
            ventaGuardada.getId(),
            ventaGuardada.getFecha(),
            ventaGuardada.getTotal(),
            ventaGuardada.getCliente().getId(),
            detallesResponse
        );
        


        
    


}

}
