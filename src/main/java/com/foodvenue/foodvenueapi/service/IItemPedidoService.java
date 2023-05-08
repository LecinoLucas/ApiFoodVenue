package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.ItemPedido;

import java.util.List;
import java.util.Optional;

public interface IItemPedidoService {

    public List<ItemPedido> findAll();
    public Optional<ItemPedido> findById(Long id);
    public ItemPedido save(ItemPedido itemPedido);
    public void deleteById(Long id);
}
