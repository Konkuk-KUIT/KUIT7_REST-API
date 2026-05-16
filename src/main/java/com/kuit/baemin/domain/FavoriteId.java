package com.kuit.baemin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class FavoriteId implements Serializable {
    private static final long serialVersionUID = -843401477898149666L;
    @NotNull
    @Column(name = "user_id2", nullable = false)
    private Long userId2;

    @NotNull
    @Column(name = "store_id", nullable = false)
    private Long storeId;


}