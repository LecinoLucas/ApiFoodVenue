package com.foodvenue.foodvenueapi.model;

public enum PedidoStatus {

    AGUARDANDO_APROVACAO,
    PREPARANDO,
    A_CAMINHO,
    ENTREGUE;

    public static PedidoStatus fromString(String status) {
        for (PedidoStatus pedidoStatus : PedidoStatus.values()) {
            if (pedidoStatus.name().equalsIgnoreCase(status)) {
                return pedidoStatus;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }
}

