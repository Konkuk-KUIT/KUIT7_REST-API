package com.kuit.baemin.domain.cart;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.Restaurant.Store;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@Table(name = "Carts")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;

    @Column(name = "orderId") // 아직 주문 전이면 NULL일 수 있으므로 일반 컬럼으로 둡니다.
    private Long orderId;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    @ColumnDefault("'ACTIVE'")
    private CartStatus status;
}