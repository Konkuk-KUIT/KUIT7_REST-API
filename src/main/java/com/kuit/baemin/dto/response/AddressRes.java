package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member_address.MemberAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRes {
    private Long addressId;
    private String name;
    private double latitude;
    private double longitude;
    private String category;

    public static AddressRes from(MemberAddress address) {
        return AddressRes.builder()
                .addressId(address.getId())
                .name(address.getAddress().getName())
                .latitude(address.getAddress().getLatitude())
                .longitude(address.getAddress().getLongitude())
                .category(address.getCategory())
                .build();
    }
}