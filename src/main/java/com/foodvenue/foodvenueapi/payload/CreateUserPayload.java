package com.foodvenue.foodvenueapi.payload;

import com.foodvenue.foodvenueapi.model.Endereco;
import com.foodvenue.foodvenueapi.model.Usuario;

public class CreateUserPayload {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;
    private Usuario.TipoUsuario tipo;

    public CreateUserPayload() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario.TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(Usuario.TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public CreateUserPayload(Long id, String nome, String email, String telefone, Endereco endereco, Usuario.TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.tipo = tipo;
    }
}
