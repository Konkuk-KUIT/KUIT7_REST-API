package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberRes {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private String grade;
    private String role;
    private String currentAddress;
    private MemberStatus status;
    private LocalDateTime createdAt;

    public static MemberRes from(Member member) {
        return MemberRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .grade(member.getGrade())
                .role(member.getRole())
                .currentAddress(member.getCurrentAddress())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .build();
    }
}