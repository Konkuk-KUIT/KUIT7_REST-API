package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import com.kuit.baemin.domain.address.AddressType;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRes {

    private Long id;
    private AddressType addressType;
    private String addressName;
    private String roadAddress;
    private String detailAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isDefault;
    private AddressStatus status;
    private Long memberId;

    public static AddressRes from(Address address) {
        return AddressRes.builder()
                .id(address.getId())
                .addressType(address.getAddressType())
                .addressName(address.getAddressName())
                .roadAddress(address.getRoadAddress())
                .detailAddress(address.getDetailAddress())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .isDefault(address.getIsDefault())
                .status(address.getStatus())
                .memberId(address.getMember().getId())
                .build();
    }
}
