package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteMemberRes {
    private Long userId;
    private MemberStatus status;

    public static DeleteMemberRes from(Member member){
        return DeleteMemberRes.builder()
                .userId(member.getId())
                .status(member.getStatus())
                .build();
    }
}
