package com.kuit.baemin.domain.menu;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Restaurant.Restaurant;
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
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 2000)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean isSoldOut;

    @Column(nullable = false)
    private Integer displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MenuStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    private MenuCategory menuCategory;

    public void update(String name, String description, BigDecimal price, String imageUrl,
                       Boolean isSoldOut, Integer displayOrder) {
        if (name != null) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
        if (price != null) {
            this.price = price;
        }
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
        if (isSoldOut != null) {
            this.isSoldOut = isSoldOut;
        }
        if (displayOrder != null) {
            this.displayOrder = displayOrder;
        }
    }
}
