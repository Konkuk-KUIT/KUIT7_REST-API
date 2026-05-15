package com.kuit.baemin.domain.Order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Store.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderMenu extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderMenuId;

    private Integer quantity;

    @Column(precision = 9, scale = 3)
    private BigDecimal orderMenuPrice;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "orderMenu", cascade = CascadeType.ALL)
    private List<OrderMenuOption> orderMenuOptions = new ArrayList<>();

}
