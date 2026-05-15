package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
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
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "order_menu_option")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuOptionId;

    @Column(nullable = false, length = 100)
    private String optionGroupName;

    @Column(nullable = false, length = 100)
    private String optionName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrderRecordStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_id", nullable = false)
    private OrderMenu orderMenu;

    void assignOrderMenu(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }
}
