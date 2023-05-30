package com.foodvenue.foodvenueapi.repository;

import com.foodvenue.foodvenueapi.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido> findByPedido_RestauranteId(Long restauranteId);
}
