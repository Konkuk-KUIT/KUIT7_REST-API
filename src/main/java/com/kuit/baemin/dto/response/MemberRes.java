package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "회원 정보 응답")
public class MemberRes {

    @Schema(description = "회원 ID", example = "1")
    private Long id;

    @Schema(description = "이메일", example = "user@example.com")
    private String email;

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Schema(description = "회원 상태")
    private MemberStatus status;

    @Schema(description = "가입일시")
    private LocalDateTime createdAt;

    public static MemberRes from(Member member) {
        return MemberRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
