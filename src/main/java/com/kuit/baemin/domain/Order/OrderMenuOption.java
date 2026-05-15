package com.kuit.baemin.domain.Order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Store.MenuDetail;
import com.kuit.baemin.domain.Store.MenuStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderMenuOption extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderOptionId;

    @Column(precision = 7, scale = 3, nullable = false)
    private BigDecimal orderOptionPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_id")
    private OrderMenu orderMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_detail_id")
    private MenuDetail menuDetail;
}
