package com.foodvenue.foodvenueapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RestauranteDTO {
    @NotBlank
    @Size(max = 255)
    private String nome;

    private String imagem;

    private String descricao;

    private boolean aberto;

    // Construtores, getters e setters

    public RestauranteDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
}