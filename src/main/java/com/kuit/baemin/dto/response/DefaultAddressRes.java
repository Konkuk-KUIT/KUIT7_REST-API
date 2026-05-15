package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultAddressRes {
    private Long userId;
    private Long defaultAddressId;
    private Boolean isDefault;
    private String addressName;

    public static DefaultAddressRes from(Address address){
        return DefaultAddressRes.builder()
                .userId(address.getMember().getId())
                .defaultAddressId(address.getId())
                .addressName(address.getAddressName())
                .isDefault(address.getIsDefault())
                .build();
    }
}
