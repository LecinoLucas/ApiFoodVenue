package com.foodvenue.foodvenueapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foodvenue.foodvenueapi.utils.EnderecoJsonConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    public Pedido(Long id, Usuario cliente, Restaurante restaurante, Endereco enderecoEntrega, PedidoStatus status, LocalDateTime dataHora) {
        this.id = id;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.enderecoEntrega = enderecoEntrega;
        this.status = status;
        this.dataHora = dataHora;
    }

    public Pedido() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Convert(converter = EnderecoJsonConverter.class)
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedido> itens;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PedidoStatus status;


    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

