package com.foodvenue.foodvenueapi.payload;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioRestauranteDTO {
    private Long restauranteId;
    private String restauranteNome;
    private BigDecimal faturamentoDiario;
    private BigDecimal faturamentoMensal;
    private BigDecimal ticketMedio;
    private Integer totalPedidos;
    private Integer totalPratosVendidos;
    private List<ClientesDTO> todosClientes;

    public RelatorioRestauranteDTO(Long restauranteId, String restauranteNome, BigDecimal faturamentoDiario, BigDecimal faturamentoMensal,
                                   BigDecimal ticketMedio, Integer totalPedidos, Integer totalPratosVendidos, List<ClientesDTO> totalClientes) {
        this.restauranteId = restauranteId;
        this.restauranteNome = restauranteNome;
        this.faturamentoDiario = faturamentoDiario;
        this.faturamentoMensal = faturamentoMensal;
        this.ticketMedio = ticketMedio;
        this.totalPedidos = totalPedidos;
        this.totalPratosVendidos = totalPratosVendidos;
        this.todosClientes = totalClientes;
    }


    public RelatorioRestauranteDTO() {
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public String getRestauranteNome() {
        return restauranteNome;
    }

    public void setRestauranteNome(String restauranteNome) {
        this.restauranteNome = restauranteNome;
    }

    public BigDecimal getFaturamentoDiario() {
        return faturamentoDiario;
    }

    public void setFaturamentoDiario(BigDecimal faturamentoDiario) {
        this.faturamentoDiario = faturamentoDiario;
    }

    public BigDecimal getFaturamentoMensal() {
        return faturamentoMensal;
    }

    public void setFaturamentoMensal(BigDecimal faturamentoMensal) {
        this.faturamentoMensal = faturamentoMensal;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public Integer getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Integer totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public Integer getTotalPratosVendidos() {
        return totalPratosVendidos;
    }

    public void setTotalPratosVendidos(Integer totalPratosVendidos) {
        this.totalPratosVendidos = totalPratosVendidos;
    }

    public List<ClientesDTO> getTodosClientes() {
        return todosClientes;
    }

    public void setTodosClientes(List<ClientesDTO> todosClientes) {
        this.todosClientes = todosClientes;
    }
}

