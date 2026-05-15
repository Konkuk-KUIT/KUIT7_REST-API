package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteAddressRes {

    private Long addressId;
    private AddressStatus status;

    public static DeleteAddressRes from(Address address){
        return DeleteAddressRes.builder()
                .addressId(address.getId())
                .status(address.getStatus())
                .build();
    }
}
