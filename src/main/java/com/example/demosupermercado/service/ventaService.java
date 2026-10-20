package com.example.demosupermercado.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public VentaResponseDTO createVenta(VentaRequestDTO request) {
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

            venta.agregarDetalle(detalle);
            total = total.add(subtotal);
        }

        venta.setTotal(total);
        Venta ventaGuardada = ventaRepository.save(venta);
        return convertirADto(ventaGuardada);
    }

    public List<VentaResponseDTO> getAllVentas() {
        List<VentaResponseDTO> response = new ArrayList<>();
        for (Venta venta : ventaRepository.findAll()) {
            response.add(convertirADto(venta));
        }
        return response;
    }

    public VentaResponseDTO getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Venta no encontrada"));
        return convertirADto(venta);
    }

    public List<VentaResponseDTO> getVentasPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RecursoNoEncontradoException("Cliente no encontrado");
        }
        List<VentaResponseDTO> response = new ArrayList<>();
        for (Venta venta : ventaRepository.findByCliente_Id(clienteId)) {
            response.add(convertirADto(venta));
        }
        return response;
    }

    public List<VentaResponseDTO> getVentasPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null || fin.isBefore(inicio)) {
            throw new IllegalArgumentException("El rango de fechas no es válido");
        }
        List<VentaResponseDTO> response = new ArrayList<>();
        for (Venta venta : ventaRepository.findByFechaBetween(inicio, fin)) {
            response.add(convertirADto(venta));
        }
        return response;
    }

    @Transactional
    public void deleteVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Venta no encontrada"));
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoRepository.save(producto);
        }
        ventaRepository.delete(venta);
    }

    private VentaResponseDTO convertirADto(Venta venta) {
        List<DetalleVentaResponseDTO> detallesResponse = venta.getDetalles().stream()
                .map(detalle -> new DetalleVentaResponseDTO(
                        detalle.getId(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.getSubtotal(),
                        detalle.getProducto().getId(),
                        detalle.getProducto().getNombre()))
                .collect(Collectors.toList());

        return new VentaResponseDTO(
                venta.getId(),
                venta.getFecha(),
                venta.getTotal(),
                venta.getCliente().getId(),
                detallesResponse);
    }

}
