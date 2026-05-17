package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.Restaurant.Store;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@Table(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Member member;

    @Column(nullable = false, length = 255)
    private String paymentMethod;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(length = 255)
    private String requests;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    @ColumnDefault("'ACCEPTED'")
    private OrderStatus status;
}