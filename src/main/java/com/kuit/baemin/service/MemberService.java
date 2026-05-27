package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.INVALID_PASSWORD;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long signUp(SignUpReq req) {
        // 이메일 중복 확인
        if (memberRepository.existsByEmail(req.getEmail())) {
            throw new MemberException(ErrorStatus.DUPLICATE_EMAIL);
        }

        Member member = Member.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .phoneNumber(req.getPhoneNumber())
                .nickname(req.getNickname())
                .status(MemberStatus.ACTIVE)
                .build();


        Member saved = memberRepository.save(member);
        return saved.getId();
    }

    /**
     * 로그인
     */
    public Long login(LoginReq req) {
        Member member = findMemberByEmail(req.getEmail());

        if (!member.getPassword().equals(req.getPassword())) {
            throw new MemberException(INVALID_PASSWORD);
        }

        return member.getId();
    }

    /**
     * 회원 단건 조회
     */
    public MemberRes getMember(Long memberId) {
        Member member = findMemberById(memberId);
        return MemberRes.from(member);
    }

    @Transactional
    public Long deleteMember(Long memberId) {
        if(!memberRepository.existsById(memberId)) {
            throw new MemberException(MEMBER_NOT_FOUND);
        }

        memberRepository.deleteById(memberId);
        return memberId;
    }

    public PageRes<MemberRes> getAllMember(Pageable pageable) {
        Page<Member> memberPage = memberRepository.findAll(pageable);

         Page<MemberRes> memberResPage = memberPage.map(MemberRes::from);

        return PageRes.from(memberResPage);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    }

    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    }
}
