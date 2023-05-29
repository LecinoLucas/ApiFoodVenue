package com.foodvenue.foodvenueapi.payload;

public class ItemPedidoDTO {
    private Long produtoId;
    private Integer quantidade;
    private String observacoes;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long produtoId, Integer quantidade, String observacoes) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
    }
}
