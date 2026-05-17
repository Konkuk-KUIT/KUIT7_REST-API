package com.kuit.baemin.domain.member;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    @ColumnDefault("'고마운분'")
    private String grade;

    @Column(nullable = false, length = 100)
    @ColumnDefault("'회원'")
    private String role;

    @Column(length = 255)
    private String currentAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 255)
    @ColumnDefault("'일반'")
    private MemberStatus status;

}