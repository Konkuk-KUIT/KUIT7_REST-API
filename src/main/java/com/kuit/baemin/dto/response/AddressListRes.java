package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AddressListRes {
    private Long userId;
    private List<AddressRes> addresses;

    public static AddressListRes of(Long userId, List<Address> addresses){
        return AddressListRes.builder()
                .userId(userId)
                .addresses(
                        addresses.stream()
                                .map(AddressRes::from)
                                .toList()
                )
                .build();
    }
}
