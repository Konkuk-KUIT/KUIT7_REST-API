package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @Column(name="order_number", length = 50, nullable = false)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="order_status", length=30, nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "delivery_fee", nullable = false)
    private Integer deliveryFee;

    @Column(name = "request_message", length = 255)
    private String requestMessage;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Builder
    public OrderEntity(
            String orderNumber,
            Integer totalPrice,
            Integer deliveryFee,
            String requestMessage,
            Member member,
            Restaurant restaurant,
            Address address
    ) {
        this.orderNumber = orderNumber;
        this.orderStatus = OrderStatus.ORDERED;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.requestMessage = requestMessage;
        this.orderedAt = LocalDateTime.now();
        this.member = member;
        this.restaurant = restaurant;
        this.address = address;

    }
}
