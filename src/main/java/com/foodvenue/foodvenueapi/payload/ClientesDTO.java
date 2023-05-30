package com.foodvenue.foodvenueapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientesDTO {
    private String nomeCliente;
    private Long quantidadePedidos;

    public ClientesDTO(String nomeCliente, Long quantidadePedidos) {
        this.nomeCliente = nomeCliente;
        this.quantidadePedidos = quantidadePedidos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(Long quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }
}

