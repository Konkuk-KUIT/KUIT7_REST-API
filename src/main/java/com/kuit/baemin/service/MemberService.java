package com.kuit.baemin.service;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.dto.request.MemberStatusReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(SignUpReq req) {
        if (memberRepository.existsByEmail(req.getEmail())) {
            throw new MemberException(ErrorStatus.DUPLICATE_EMAIL);
        }

        Member member = Member.builder()
                .email(req.getEmail())
                .nickname(req.getNickname())
                .phoneNumber(req.getPhoneNumber())
                .status(MemberStatus.ACTIVE)
                .build();

        return memberRepository.save(member).getId();
    }

    public MemberRes getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberRes.from(member);
    }

    @Transactional
    public Long updateStatus(Long memberId, MemberStatusReq req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        member.updateStatus(req.getStatus());
        return member.getId();
    }
}
