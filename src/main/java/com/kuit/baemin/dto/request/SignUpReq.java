package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReq {

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Size(max = 100, message = "이메일은 100자 이하여야 합니다.") // DB 크기 반영
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
    private String password;

    @NotBlank(message = "전화번호는 필수입니다.")
    // DB varchar(11)에 맞게 하이픈 없이 숫자 11자리만 허용하는 패턴으로 변경
    @Pattern(regexp = "^010\\d{8}$", message = "올바른 전화번호 형식이 아닙니다. (숫자 11자리 입력)")
    private String phoneNumber;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.") // nickname -> userName 변경 반영
    private String userName;
}
