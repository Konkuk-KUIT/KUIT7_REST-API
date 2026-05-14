package com.kuit.baemin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long id;

    @Size(max = 225)
    @NotNull
    @Column(name = "name", nullable = false, length = 225)
    private String name;

    @Size(max = 225)
    @Column(name = "operating_hours", length = 225)
    private String operatingHours;

    @Size(max = 225)
    @Column(name = "closed_days", length = 225)
    private String closedDays;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 225)
    @Column(name = "amenities", length = 225)
    private String amenities;

    @Size(max = 225)
    @Column(name = "notice", length = 225)
    private String notice;

    @Size(max = 225)
    @Column(name = "category", length = 225)
    private String category;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "min_order_amount", nullable = false)
    private Integer minOrderAmount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "favorite_count", nullable = false)
    private Integer favoriteCount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "review_count", nullable = false)
    private Integer reviewCount;

    @Size(max = 225)
    @Column(name = "introduce", length = 225)
    private String introduce;

    @Size(max = 225)
    @NotNull
    @Column(name = "store_address", nullable = false, length = 225)
    private String storeAddress;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Size(max = 255)
    @Column(name = "status")
    private String status;


}