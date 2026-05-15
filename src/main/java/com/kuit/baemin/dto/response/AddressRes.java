package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRes {
    private Long id;
    private String roadAddress;
    private String detailAddress;
    private Boolean isDefault;

    public static AddressRes from(Address address) {
        return AddressRes.builder()
                .id(address.getId())
                .roadAddress(address.getRoadAddress())
                .detailAddress(address.getDetailAddress())
                .isDefault(address.getIsDefault())
                .build();
    }
}