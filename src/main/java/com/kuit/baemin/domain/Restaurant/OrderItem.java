package com.kuit.baemin.domain.Restaurant;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false, length = 100)
    private String menuNameSnapshot;

    @Column(nullable = false)
    private Long menuPriceSnapshot;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, length = 20)
    private String status;

    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();
}
