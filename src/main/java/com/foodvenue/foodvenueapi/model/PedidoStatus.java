package com.foodvenue.foodvenueapi.model;

public enum PedidoStatus {
    PREPARANDO("preparando"),
    A_CAMINHO("a caminho"),
    ENTREGUE("entregue");

    private String status;

    PedidoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // Método para converter a string em um enum válido
    public static PedidoStatus fromString(String status) {
        for (PedidoStatus pedidoStatus : PedidoStatus.values()) {
            if (pedidoStatus.status.equalsIgnoreCase(status)) {
                return pedidoStatus;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }

    @Override
    public String toString() {
        return this.status;
    }
}
