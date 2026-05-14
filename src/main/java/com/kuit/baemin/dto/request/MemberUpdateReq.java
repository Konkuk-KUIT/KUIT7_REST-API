package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateReq {

    @Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
    private String userName;

    @Pattern(regexp = "^010\\d{8}$", message = "올바른 전화번호 형식이 아닙니다.")
    private String phoneNumber;
}
