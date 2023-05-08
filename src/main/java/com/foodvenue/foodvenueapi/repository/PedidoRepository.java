package com.foodvenue.foodvenueapi.repository;

import com.foodvenue.foodvenueapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByRestauranteId(Long restauranteId);
}
