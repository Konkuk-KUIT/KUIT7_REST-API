package com.kuit.baemin.domain.member;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter
@Builder
@Table(name = "`user`")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // DB 컬럼명에 맞춤
    private Long id;

    @Column(name = "user_password", nullable = false, length = 200)
    private String password;

    @Column(name = "user_name", nullable = false, length = 200)
    private String userName; // nickname 대신 userName으로 통일

    @Column(nullable = false, length = 11) // 길이 11로 수정
    private String phoneNumber;

    @Column(nullable = false, length = 100) // 길이 100으로 수정
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 100)
    private MemberStatus status;

    // 회원 소프트 삭제 처리
    public void withdraw() {
        this.status = MemberStatus.DELETED;
    }


    public void updateProfile(String userName , String phoneNumber){
        if(userName !=null ){this.userName = userName;}
        if(phoneNumber != null){this.phoneNumber = phoneNumber;}

    }

}
