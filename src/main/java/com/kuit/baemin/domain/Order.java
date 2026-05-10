package com.kuit.baemin.domain;

import com.kuit.baemin.domain.member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Size(max = 225)
    @Column(name = "request", length = 225)
    private String request;

    @Size(max = 20)
    @NotNull
    @Column(name = "receive_way", nullable = false, length = 20)
    private String receiveWay;

    @NotNull
    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @NotNull
    @Column(name = "estimate_time", nullable = false)
    private Integer estimateTime;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;


}