package com.kuit.baemin.domain.Restaurant;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "order_item_options")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id", nullable = false)
    private MenuOption menuOption;

    @Column(nullable = false, length = 100)
    private String optionNameSnapshot;

    @Column(nullable = false)
    private Long optionPriceSnapshot;
}
