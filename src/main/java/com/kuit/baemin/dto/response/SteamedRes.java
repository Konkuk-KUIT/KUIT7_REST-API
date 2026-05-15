package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Steamed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SteamedRes {
    private Long steamedId;
    private Long restaurantId;
    private String restaurantName;
    private LocalDateTime createdAt;

    public static SteamedRes from(Steamed steamed){
        return SteamedRes.builder()
                .steamedId(steamed.getId())
                .restaurantId(steamed.getStore().getId())
                .restaurantName(steamed.getStore().getName())
                .createdAt(steamed.getCreatedAt())
                .build();
    }
}
