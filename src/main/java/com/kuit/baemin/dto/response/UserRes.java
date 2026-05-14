package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRes {

    private Long id;
    private String email;
    private String phoneNumber;
    private String nickname;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static UserRes from(User user) {
        return UserRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
