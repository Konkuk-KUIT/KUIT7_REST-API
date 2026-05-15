package com.kuit.baemin.domain.menu;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="menus")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "is_sold_out", nullable = false)
    private Boolean isSoldOut;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private MenuStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Restaurant restaurant;

    @Builder
    public Menu(
            String name,
            Integer price,
            String description,
            Boolean isSoldOut,
            Restaurant restaurant
    ) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isSoldOut = isSoldOut != null ? isSoldOut : false;
        this.restaurant = restaurant;
        this.status = MenuStatus.ACTIVE;
    }
}

