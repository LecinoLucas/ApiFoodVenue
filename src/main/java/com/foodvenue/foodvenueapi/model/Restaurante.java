package com.foodvenue.foodvenueapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurantes")
@Data
public class Restaurante {
    public Restaurante() {
    }

    public Restaurante(Long id, Usuario usuario, String nome, String descricao, String imagem, boolean aberto) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.aberto = aberto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    @NotBlank
    @Size(max = 255)
    private String nome;

    @Lob
    private String descricao;

    private String imagem;

    private boolean aberto;
}
