package com.kuit.baemin.dto.request;

import com.kuit.baemin.domain.address.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class AddressCreateReq {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "주소 유형은 필수입니다.")
    private AddressType addressType;

    @Size(max = 50, message = "주소 이름은 50자 이하여야 합니다.")
    private String addressName;

    @NotBlank(message = "도로명 주소는 필수입니다.")
    @Size(max = 100, message = "도로명 주소는 100자 이하여야 합니다.")
    private String roadAddress;

    @Size(max = 100, message = "상세 주소는 100자 이하여야 합니다.")
    private String detailAddress;

    @NotNull(message = "위도는 필수입니다.")
    private BigDecimal latitude;

    @NotNull(message = "경도는 필수입니다.")
    private BigDecimal longitude;

    @NotNull(message = "기본 주소 여부는 필수입니다.")
    private Boolean isDefault;
}
