package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberRes {

    private Long userId; // 프론트엔드 반환 시 명확하도록 id -> userId로 변경
    private String email;
    private String phoneNumber;
    private String userName;
    private Long userPoint; // 포인트 조회 추가
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static MemberRes from(Member member) {
        return MemberRes.builder()
                .userId(member.getId())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .userName(member.getUserName())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
