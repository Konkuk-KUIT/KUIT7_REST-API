package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRes {

    private Long id;
    private Long memberId;
    private String addressType;
    private String address;
    private Double latitude;
    private Double longitude;

    public static AddressRes from(Address address) {
        return AddressRes.builder()
                .id(address.getId())
                .memberId(address.getMember().getId())
                .addressType(address.getAddressType())
                .address(address.getAddress())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }
}
