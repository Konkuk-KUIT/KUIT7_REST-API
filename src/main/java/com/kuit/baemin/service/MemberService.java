package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.INVALID_PASSWORD;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.request.UpdateMemberReq;
import com.kuit.baemin.dto.response.DeleteMemberRes;
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
                .phone(req.getPhone())
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
        Member member = memberRepository
                .findByEmailAndStatus(req.getEmail(), MemberStatus.ACTIVE)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        if (!member.getPassword().equals(req.getPassword())) {
            throw new MemberException(INVALID_PASSWORD);
        }

        return member.getId();
    }

    /**
     * 회원 단건 조회
     */
    public MemberRes getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        return MemberRes.from(member);
    }

    public MemberRes updateMember(Long userId, UpdateMemberReq req){
        if(req.isEmpty()){
            throw new MemberException(ErrorStatus.EMPTY_UPDATE_VALUE);
        }
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        member.updateInfo(
                req.getName(),
                req.getNickname(),
                req.getPhone()
        );
        return MemberRes.from(member);
    }
    public DeleteMemberRes deleteMember(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        if (member.isDeleted()) {
            throw new MemberException(ErrorStatus.MEMBER_ALREADY_DELETED);
        }
        member.delete();
        return DeleteMemberRes.from(member);
    }
}
