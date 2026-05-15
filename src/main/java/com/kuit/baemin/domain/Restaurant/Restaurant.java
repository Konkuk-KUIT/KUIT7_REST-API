package com.kuit.baemin.domain.Restaurant;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter
@Builder
@Table(name = "stores")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(name = "category", length = 50, nullable = false)
    private String category;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "min_order_price", nullable = false)
    private Integer minOrderPrice = 0;

    @Column(name = "delivery_fee", nullable = false)
    private Integer deliveryFee = 0;

    @Column(name = "rating", precision = 2, scale = 1, nullable = false)
    private BigDecimal rating;

    @Column(name = "is_open", nullable = false)
    private Boolean isOpen;

    @Column(name = "latitude", precision = 10, scale = 7, nullable = false)
    private BigDecimal latitude;

    @Column(name="description", length = 255)
    private String description;

    @Column(name = "longitude", precision = 10, scale = 7, nullable = false)
    private BigDecimal longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private RestaurantStatus status = RestaurantStatus.ACTIVE;

}
