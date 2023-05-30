package com.foodvenue.foodvenueapi.repository;

import com.foodvenue.foodvenueapi.model.Pedido;
import com.foodvenue.foodvenueapi.model.PedidoStatus;
import com.foodvenue.foodvenueapi.payload.ClientesDTO;
import com.foodvenue.foodvenueapi.payload.PratoVendidoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);

    List<Pedido> findByRestauranteId(Long restauranteId);

    Optional<Pedido> findFirstByClienteIdAndStatusNotInOrderByDataHoraDesc(Long clienteId, List<PedidoStatus> status);

    @Query("SELECT ip.prato.id AS pratoId, ip.prato.nome AS nomePrato, SUM(ip.quantidade) AS quantidadeVendida FROM ItemPedido ip WHERE ip.pedido.restaurante.id = :restauranteId GROUP BY ip.prato.id, ip.prato.nome ORDER BY quantidadeVendida DESC")
    List<PratoVendidoDTO> findPratosMaisVendidosByRestauranteId(@Param("restauranteId") Long restauranteId);

    @Query("SELECT new com.foodvenue.foodvenueapi.payload.ClientesDTO(p.cliente.nome, COUNT(p.cliente.id)) " +
            "FROM Pedido p " +
            "WHERE p.restaurante.id = :restauranteId " +
            "GROUP BY p.cliente.nome " +
            "ORDER BY COUNT(p.cliente.id) DESC")
    List<ClientesDTO> findClientesByRestauranteId(@Param("restauranteId") Long restauranteId);

    @Query("SELECT SUM(ip.prato.preco * ip.quantidade) " +
            "FROM Pedido p JOIN p.itens ip " +
            "WHERE p.restaurante.id = :restauranteId " +
            "AND MONTH(p.dataHora) = MONTH(CURRENT_DATE) " +
            "AND YEAR(p.dataHora) = YEAR(CURRENT_DATE)")
    BigDecimal calcularFaturamentoMensal(@Param("restauranteId") Long restauranteId);

    @Query("SELECT SUM(ip.quantidade) " +
            "FROM ItemPedido ip JOIN ip.pedido p " +
            "WHERE p.restaurante.id = :restauranteId " +
            "AND MONTH(p.dataHora) = MONTH(CURRENT_DATE) " +
            "AND YEAR(p.dataHora) = YEAR(CURRENT_DATE)")
    Long calcularTotalPratosVendidos(@Param("restauranteId") Long restauranteId);

    @Query("SELECT AVG(ip.prato.preco) FROM Pedido p JOIN p.itens ip WHERE p.restaurante.id = :restauranteId")
    BigDecimal calcularTicketMedio(@Param("restauranteId") Long restauranteId);

    @Query("SELECT SUM(ip.prato.preco * ip.quantidade) FROM Pedido p JOIN p.itens ip WHERE p.restaurante.id = :restauranteId AND DATE(p.dataHora) = CURRENT_DATE")
    BigDecimal calcularFaturamentoDiario(@Param("restauranteId") Long restauranteId);

}