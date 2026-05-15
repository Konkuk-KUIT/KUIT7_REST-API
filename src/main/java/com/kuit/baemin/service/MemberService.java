package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.INVALID_PASSWORD;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.MemberUpdateReq;
import com.kuit.baemin.dto.request.SignUpReq;
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
                .userName(req.getUserName())
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

    @Transactional
    public Long updateMember(Long memberId, MemberUpdateReq req){
        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        if (req.getPhoneNumber() != null){
            boolean isDuplicate = memberRepository.existsByPhoneNumber(req.getPhoneNumber());
            if (isDuplicate){
                throw new MemberException(ErrorStatus.DUPLICATE_PHONE_NUMBER);
            }
        }

        member.updateProfile(req.getUserName() , req.getPhoneNumber());

        return member.getId();

    }

    @Transactional
    public DeleteMemberRes deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        if (MemberStatus.DELETED.equals(member.getStatus())) {
            throw new MemberException(ErrorStatus.ALREADY_DELETED_MEMBER); // "이미 탈퇴한 회원입니다."
        }

        member.withdraw();

        return DeleteMemberRes.builder()
                .memberId(member.getId())
                .build();

    }


}
