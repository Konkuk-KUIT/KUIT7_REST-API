package com.kuit.baemin.domain.Store;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, length = 50)
    private String storeName;

    @Column(nullable = false, length = 10)
    private String storeCategory;

    @Column(nullable = false, length = 50)
    private String storeAddress;

    private Integer deliveryMinimum;
    private String operatingTime;
    private String restInformation;
    private String storeTelephone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StoreStatus status;
}
