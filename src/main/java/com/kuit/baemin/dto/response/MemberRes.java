package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRes {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private MemberStatus status;
    private LocalDateTime createdAt;

    public static MemberRes from(Member member) {
        return MemberRes.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
