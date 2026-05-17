package com.kuit.baemin.domain.review;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.Restaurant.Store;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@Table(name = "Reviews")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId")
    private Menu menu;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 255)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String reviewPictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    @ColumnDefault("'ACTIVE'")
    private ReviewStatus status;
}