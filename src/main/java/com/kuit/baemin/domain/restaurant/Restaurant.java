package com.kuit.baemin.domain.restaurant;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String restaurantName;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false)
    private Float minOrderAmount;

    @Column(nullable = false)
    private Float deliveryFee;

    @Column(nullable = false)
    private Float rate;

    @Column(nullable = false)
    private Boolean isOpen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private RestaurantStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();
}