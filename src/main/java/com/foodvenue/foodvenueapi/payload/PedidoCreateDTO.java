package com.foodvenue.foodvenueapi.payload;

import com.foodvenue.foodvenueapi.model.Endereco;

import java.util.List;

public class PedidoCreateDTO {
    private Long clienteId;
    private Long restauranteId;
    private Endereco enderecoEntrega;
    private List<ItemPedidoDTO> itens;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public PedidoCreateDTO(Long clienteId, Long restauranteId, Endereco enderecoEntrega, List<ItemPedidoDTO> itens) {
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = itens;
    }

    public PedidoCreateDTO() {
    }
}
