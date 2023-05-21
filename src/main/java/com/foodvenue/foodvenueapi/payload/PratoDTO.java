package com.foodvenue.foodvenueapi.payload;

import com.foodvenue.foodvenueapi.model.Restaurante;

import java.math.BigDecimal;

public class PratoDTO {
    private String nome;
    private Restaurante restaurante;
    private String descricao;

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    private BigDecimal preco;
    private String imagem;



    // Construtores, getters e setters

    public PratoDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
// Outros métodos, se necessário
}
