package com.kuit.baemin.dto.response;


import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRes {
    private Long addressId;
    private Long userId;
    private String addressName;
    private String address;
    private Boolean isDefault;
    private Double latitude;
    private Double longitude;
    private AddressStatus status;

    public static AddressRes from(Address address) {
        return AddressRes.builder()
                .addressId(address.getId())
                .userId(address.getMember().getId())
                .addressName(address.getAddressName())
                .address(address.getAddress())
                .isDefault(address.getIsDefault())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .status(address.getStatus())
                .build();
    }
}
