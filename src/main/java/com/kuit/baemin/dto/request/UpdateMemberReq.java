package com.kuit.baemin.dto.request;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMemberReq {
    @Size(max=50, message="이름은 50자 이하여야 합니다.")
    private String name;

    @Size(max = 25, message = "닉네임은 25자 이하여야 합니다.")
    private String nickname;

    @Pattern(
            regexp = "^01[016789]-\\d{3,4}-\\d{4}$",
            message = "올바른 전화번호 형식이 아닙니다. ex) 010-1234-5678"
    )
    private String phone;

    public boolean isEmpty(){
        return name == null && nickname == null && phone==null;
    }
}
