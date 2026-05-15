package com.kuit.baemin.domain.order;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.CascadeType;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "order_menu")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long menuId;

    @Column(nullable = false, length = 100)
    private String menuName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private OrderRecordStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_order_id", nullable = false)
    private DeliveryOrder deliveryOrder;

    @Builder.Default
    @OneToMany(mappedBy = "orderMenu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenuOption> orderMenuOptions = new ArrayList<>();

    void assignDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public void addOrderMenuOption(OrderMenuOption orderMenuOption) {
        orderMenuOptions.add(orderMenuOption);
        orderMenuOption.assignOrderMenu(this);
    }
}
