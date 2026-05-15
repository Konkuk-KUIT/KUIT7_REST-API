package com.kuit.baemin.domain.Order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order는 SQL 예약어이므로 테이블명 명시 권장
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String payMethod;

    @Column(precision = 10, scale = 3)
    private BigDecimal discount;

    @Column(nullable = false, length = 200, columnDefinition = "VARCHAR(200) DEFAULT '문 앞에 두면 가져갈게요(벨, 노크 X)'")
    private String request;

    @Column(nullable = false, length = 200, columnDefinition = "VARCHAR(200) DEFAULT '수저, 포크 X'")
    private String storeRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrderStatus status;

    public void addOrderMenu(OrderMenu orderMenu) {
        // 1. 부모(Order)의 리스트에 자식(OrderMenu) 추가
        this.orderMenus.add(orderMenu);

        // 2. 자식(OrderMenu) 객체에도 부모(Order)를 설정 (가장 중요!)
        // 자식 엔티티의 @ManyToOne 필드인 order를 업데이트합니다.
        if (orderMenu.getOrder() != this) {
            orderMenu.setOrder(this);
        }
    }
}
