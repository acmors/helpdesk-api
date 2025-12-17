package com.helpdesk_api.domain.enums;

public enum Status {
    PENDENTE("PENDENTE"),
    EM_ANDAMENTO("EM ANDAMENTO"),
    RESOLVIDO("RESOLVIDO"),
    CANCELADO("CANCELADO"),
    ENCERRADO("ENCERRADO");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
