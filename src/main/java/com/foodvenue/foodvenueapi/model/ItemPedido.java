package com.foodvenue.foodvenueapi.model;
import javax.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    public ItemPedido(Long id, Pedido pedido, Prato prato, Integer quantidade, String observacoes) {
        this.id = id;
        this.pedido = pedido;
        this.prato = prato;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
    }

    public ItemPedido() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "prato_id")
    private Prato prato;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "observacoes")
    private String observacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
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
}
