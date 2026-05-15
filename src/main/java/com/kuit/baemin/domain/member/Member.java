package com.kuit.baemin.domain.member;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(length = 50)
    private String primary_address;

    @Column(name = "profile_image", length = 300)
    private String profileImage;

    @Column(name= "primary_email", length = 255)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MemberStatus status;

    @Column
    private Long address_id;

}
