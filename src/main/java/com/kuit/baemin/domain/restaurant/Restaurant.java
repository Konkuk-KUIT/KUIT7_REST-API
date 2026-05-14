package com.kuit.baemin.domain.restaurant;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter
@Builder
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 음식점 이름
    @Column(nullable = false, length = 100)
    private String name;

    // 전화번호
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    // 도로명 주소
    @Column(name = "road_address", nullable = false, length = 100)
    private String roadAddress;

    // 상세 주소
    @Column(name = "detail_address", length = 100)
    private String detailAddress;

    // 위도
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude;

    // 경도
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude;

    // 최소 주문 금액
    @Column(name = "min_order_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal minOrderPrice;

    // 배달비
    @Column(name = "delivery_fee", nullable = false, precision = 15, scale = 2)
    private BigDecimal deliveryFee;

    // 음식점 이미지
    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    // 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RestaurantStatus status;

    // 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
