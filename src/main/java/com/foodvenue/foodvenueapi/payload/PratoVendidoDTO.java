package com.foodvenue.foodvenueapi.payload;

public class PratoVendidoDTO {
    private Long pratoId;
    private String nomePrato;
    private int quantidadeVendida;

    public Long getPratoId() {
        return pratoId;
    }

    public void setPratoId(Long pratoId) {
        this.pratoId = pratoId;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public PratoVendidoDTO() {
    }

    public PratoVendidoDTO(Long pratoId, String nomePrato, int quantidadeVendida) {
        this.pratoId = pratoId;
        this.nomePrato = nomePrato;
        this.quantidadeVendida = quantidadeVendida;
    }
}
