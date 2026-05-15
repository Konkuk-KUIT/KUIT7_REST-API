package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.domain.order_menu.OrderMenu;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @Column(name = "orderId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @Column(length = 100)
    private String request;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrderStatus status;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Builder.Default
    @OneToMany(mappedBy = "order") // Menu의 restaurant 필드와 연결
    private List<OrderMenu> menus = new ArrayList<>();

    public void addOrderMenu(OrderMenu orderMenu) {
        this.menus.add(orderMenu); // 내 장바구니 리스트에 자식 추가
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }
}
