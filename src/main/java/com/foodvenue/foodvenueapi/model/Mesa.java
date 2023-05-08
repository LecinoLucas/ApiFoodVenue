package com.foodvenue.foodvenueapi.model;

import javax.persistence.*;

@Entity
@Table(name = "mesas")
public class Mesa {
    public Mesa(Long id, Long restauranteId, Integer numero, Integer capacidade, Boolean disponivel) {
        this.id = id;
        this.restauranteId = restauranteId;
        this.numero = numero;
        this.capacidade = capacidade;
        this.disponivel = disponivel;
    }

    public Mesa() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurante_id")
    private Long restauranteId;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "capacidade")
    private Integer capacidade;

    @Column(name = "disponivel")
    private Boolean disponivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
