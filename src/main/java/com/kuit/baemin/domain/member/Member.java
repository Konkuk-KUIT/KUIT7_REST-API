package com.kuit.baemin.domain.member;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// TODO: 본인이 설계한 ERD에 맞게 수정
@Entity
@Getter
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 50)
    private String nickname;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberStatus status;

    public void updateInfo(String name, String nickname, String phone){
        if(name != null){
            this.name = name;
        }
        if(name != null){
            this.nickname = nickname;
        }
        if(phone!=null){
            this.phone = phone;
        }
    }

    public void delete(){
        this.status = MemberStatus.DELETED;
    }
    public boolean isDeleted(){
        return this.status == MemberStatus.DELETED;
    }
}
