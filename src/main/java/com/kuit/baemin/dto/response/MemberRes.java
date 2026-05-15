package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberRes {

    private Long id;
    private String email;
    private String phone;
    private String nickname;
    private String name;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static MemberRes from(Member member) {
        return MemberRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .phone(member.getPhone())
                .name(member.getName())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
