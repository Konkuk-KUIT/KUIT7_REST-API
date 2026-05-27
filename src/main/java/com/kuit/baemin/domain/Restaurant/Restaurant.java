package com.kuit.baemin.domain.Restaurant;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "store")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private Long minimum_delivery_price;

    @Column
    private Long order_count;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private RestaurantStatus status;

    @Column
    private Long address_id;

    public void updateName (String newName) {
        this.name = newName;
    }

    public void updateMinimumDeliveryPrice (Long delivery_price) {
        this.minimum_delivery_price = delivery_price;
    }

}
