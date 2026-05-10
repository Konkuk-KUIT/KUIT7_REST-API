package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRes {
  private Long id;
  private String address;
  private String detailAddress;
  private Boolean isDefault;

  public static AddressRes from(Address address) {
    return new AddressRes(
        address.getId(),
        address.getAddress(),
        address.getDetailAddress(),
        address.getIsDefault()
    );
  }
}
