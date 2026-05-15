package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.User.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRes {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public static UserRes from(User user) {
        return UserRes.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
