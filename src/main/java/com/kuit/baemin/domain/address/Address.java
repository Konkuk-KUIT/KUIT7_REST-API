package com.kuit.baemin.domain.address;


import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@Table(name = "address") // DB 테이블 이름 "user"에 정확히 맞춤
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "address_category", nullable = false, length = 100)
    private String addressCategory;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @Column(name = "entrance_password")
    private String entrancePassword;

    @Column(name = "user_latitude", nullable = false, precision = 18, scale = 10)
    private BigDecimal userLatitude;

    @Column(name = "user_longitude", nullable = false, precision = 18, scale = 10)
    private BigDecimal userLongitude;

    @Column(name = "status", nullable = false, length = 100)
    @Builder.Default
    private String status = "ACTIVE";


}
