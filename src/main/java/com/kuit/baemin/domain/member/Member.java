package com.kuit.baemin.domain.member;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.order_menu.OrderMenu;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter
@Builder
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    @Column(length = 25)
    private String name;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "member") // Menu의 restaurant 필드와 연결
    private List<Order> orders = new ArrayList<>();

}
