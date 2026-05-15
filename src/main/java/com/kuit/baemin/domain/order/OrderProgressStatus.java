package com.kuit.baemin.domain.order;

public enum OrderProgressStatus {
    PLACED,
    ACCEPTED,
    DELIVERING,
    COMPLETED,
    CANCELED;

    public boolean isCancelable() {
        return this == PLACED;
    }
}
