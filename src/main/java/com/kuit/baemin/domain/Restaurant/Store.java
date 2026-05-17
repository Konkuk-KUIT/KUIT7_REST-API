package com.kuit.baemin.domain.Restaurant;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import java.math.BigDecimal;

@Entity
@Getter
@Builder
@Table(name = "Stores")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer type;

    @Column(nullable = false, length = 20)
    private String category;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 255)
    private String content;

    @Column(nullable = false)
    private Integer minDeliveryPrice;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer deliveryTip;

    private Integer minDeliveryTime;
    private Integer maxDeliveryTime;

    @Column(nullable = false, precision = 2, scale = 1)
    @ColumnDefault("0.0")
    private BigDecimal rating;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer reviewCount;

    @Column(length = 255)
    private String operationHours;

    @Column(length = 255)
    private String closedDays;

    @Column(length = 255)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    @ColumnDefault("'ACTIVE'")
    private StoreStatus status;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;
}