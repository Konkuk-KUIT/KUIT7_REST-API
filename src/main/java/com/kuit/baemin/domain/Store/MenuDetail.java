package com.kuit.baemin.domain.Store;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_detail")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MenuDetail extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuDetailId;

    @Column(name = "menu_option", length = 30)
    private String option;

    private String content;

    @Column(precision = 7, scale = 3, nullable = false)
    private BigDecimal optionCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MenuStatus status;
}
