package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressReq {
  @NotBlank(message = "주소는 필수입니다.")
  private String address;

  private String detailAddress;

  @NotNull(message = "기본 배달지 설정은 필수입니다.")
  private Boolean isDefault;
}
