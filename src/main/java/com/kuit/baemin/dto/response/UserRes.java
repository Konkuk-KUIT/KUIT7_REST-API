package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.User.User;
import com.kuit.baemin.domain.User.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRes {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    public static UserRes from(User user) {
        return UserRes.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
