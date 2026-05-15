package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false, length = 200)
    private String deliveryRoadAddress;

    @Column(length = 200)
    private String deliveryDetailAddress;

    @Column(nullable = false)
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private OrderProgressStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addItem(OrderItem item) {
        this.orderItems.add(item);
        item.assignOrder(this);
    }

    public int itemsSubtotal() {
        return this.orderItems.stream().mapToInt(OrderItem::subtotal).sum();
    }

    public void finalizeTotal(int deliveryFee) {
        this.totalPrice = itemsSubtotal() + deliveryFee;
    }

    public void cancel() {
        if (!this.orderStatus.isCancelable()) {
            throw new GeneralException(ErrorStatus.ORDER_NOT_CANCELABLE);
        }
        this.orderStatus = OrderProgressStatus.CANCELED;
    }

    public boolean isOwnedBy(Long memberId) {
        return this.member.getId().equals(memberId);
    }
}
