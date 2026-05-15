package com.kuit.baemin.domain;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.steamed.SteamedStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "steamed")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Steamed extends BaseEntity{

    @Id
    @Column(name = "steamd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="store_id")
    private Restaurant store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SteamedStatus status;
}
