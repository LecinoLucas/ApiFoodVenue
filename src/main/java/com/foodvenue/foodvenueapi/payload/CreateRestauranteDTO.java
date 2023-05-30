package com.foodvenue.foodvenueapi.payload;

import com.foodvenue.foodvenueapi.model.Usuario;

public class CreateRestauranteDTO {
    private boolean aberto;
    private String descricao;
    private Usuario usuario;
    private String nome;

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
