package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.User.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRes {

    private Long userId;
    private String email;
    private String phoneNumber;
    private String userName;
    private String profileImage;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static UserRes from(User user) {
        return UserRes.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userName(user.getUserName())
                .profileImage(user.getProfileImage())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
