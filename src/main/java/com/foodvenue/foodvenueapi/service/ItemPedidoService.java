package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.ItemPedido;
import com.foodvenue.foodvenueapi.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService implements IItemPedidoService{
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> findAll() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> findById(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public ItemPedido save(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void deleteById(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
