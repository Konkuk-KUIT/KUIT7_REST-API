package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.menu.MenuOption;
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

    // 주문한 메뉴 하나에 선택된 옵션
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    // 원본 메뉴 옵션 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id", nullable = false)
    private MenuOption menuOption;

    // 주문 당시 옵션 정보 스냅샷
    @Column(nullable = false, length = 100)
    private String optionGroup;

    @Column(nullable = false, length = 100)
    private String optionName;

    @Column(nullable = false)
    private Integer additionalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrderItemOptionStatus status;
}
