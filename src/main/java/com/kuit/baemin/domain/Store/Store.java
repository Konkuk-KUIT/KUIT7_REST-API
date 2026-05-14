package com.kuit.baemin.domain.Store;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter
@Builder
@Table(name = "store")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", nullable = false, length = 100)
    private String name;

    @Column(name = "store_address", nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 11)
    private String storeNumber;

    @Column(nullable = false)
    private int favoriteCount;

    @Column(nullable = false, length = 100)
    private String operationHour;

    @Column(length = 200)
    private String deliveryArea;


    @Column(nullable = false, precision = 18, scale = 10)
    private BigDecimal storeLatitude;

    @Column(nullable = false, precision = 18, scale = 10)
    private BigDecimal storeLongitude;

    // DB의 decimal(10,2) 매핑
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minPrice;

    @Column(nullable = false)
    @Builder.Default
    private int reviewCount = 0;

    @Column(nullable = false, precision = 2, scale = 1)
    @Builder.Default
    private BigDecimal averageRating = BigDecimal.ZERO;

    // ---------------------------------------------------

    public void updateAverageRating(int newRating){
        BigDecimal totalScore = this.averageRating.multiply(new BigDecimal(this.reviewCount));
        this.reviewCount += 1;
        BigDecimal newTotalScore = totalScore.add(new BigDecimal(newRating));
        this.averageRating = newTotalScore.divide(new BigDecimal(this.reviewCount), 1, RoundingMode.HALF_UP);

    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 200) // DB 사진의 varchar(200)에 맞춤
    private StoreStatus status;

}
