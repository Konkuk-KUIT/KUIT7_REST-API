package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateAddressReq {
    @Size(max=50, message=" 주소 이름은 50자 이하여야 합니다.")
    private String addressName;

    @Size(max=255, message="주소는 255자 이하여야 합니다.")
    private String address;
    private Boolean isDefault;
    private Double latitude;
    private Double longitude;
    public boolean isEmpty(){
        return addressName == null
                && address == null
                && isDefault == null
                && latitude == null
                && longitude == null;
    }
}
