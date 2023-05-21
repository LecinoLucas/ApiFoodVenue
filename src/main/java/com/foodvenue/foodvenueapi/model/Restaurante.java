package com.foodvenue.foodvenueapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.sql.SQLException;

@Entity
@Table(name = "restaurantes")
@Data
public class Restaurante {
    public Restaurante() {
    }

    public Restaurante(Long id, Usuario usuario, String nome, String descricao, byte[] imagem, boolean aberto) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.descricao = descricao;
        this.imagemBytes = imagem;
        this.aberto = aberto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @Lob
    @Column(name = "imagem_blob")
    private Blob imagem;

    // Getter e Setter para o campo imagemBytes
    @Transient
    private byte[] imagemBytes;

    private String descricao;

    private boolean aberto;

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

    public byte[] getImagemBytes() {
        return imagemBytes;
    }

    public void setImagemBytes(byte[] imagemBytes) {
        this.imagemBytes = imagemBytes;
        try {
            this.imagem = new javax.sql.rowset.serial.SerialBlob(imagemBytes);
        } catch (SQLException e) {
            // Tratar o erro, se necessário
        }
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
