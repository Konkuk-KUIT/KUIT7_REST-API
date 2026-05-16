package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberRes {

    private Long id;
    private String email;
    private String phoneNumber;
    private String nickname;
    private String profileImage;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static MemberRes from(Member member) {
        return MemberRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
