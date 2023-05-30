package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {

    public List<Pedido> findAll();
    public Pedido findById(Long id);
    public List<Pedido> findByClienteId(Long clienteId);
    public List<Pedido> findByRestauranteId(Long restauranteId);
    public Pedido save(Pedido pedido);
    public Pedido update(Long id, Pedido pedido);
    public Optional<Pedido> findPedidosNotEntregueOrCancelado(Long clienteId);
    public void delete(Long id) ;
}
