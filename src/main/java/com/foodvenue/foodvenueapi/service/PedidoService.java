package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Pedido;
import com.foodvenue.foodvenueapi.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new Error("Pedido não encontrado"));
    }

    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> findByRestauranteId(Long restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido update(Long id, Pedido pedido) {
        Pedido existingPedido = findById(id);
        BeanUtils.copyProperties(pedido, existingPedido, "id");
        return pedidoRepository.save(existingPedido);
    }

    public void delete(Long id) {
        Pedido pedido = findById(id);
        pedidoRepository.delete(pedido);
    }
}
