package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRes {

    private Long addressId;
    private Long memberId;
    private String address;
    private String addressName;
    private AddressStatus status;

    public static AddressRes from(Address address) {
        return AddressRes.builder()
                .addressId(address.getId())
                .memberId(address.getMember().getId())
                .address(address.getAddress())
                .addressName(address.getAddressName())
                .status(address.getStatus())
                .build();
    }
}
