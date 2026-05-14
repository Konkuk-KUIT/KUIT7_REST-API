package com.kuit.baemin.service;

import com.kuit.baemin.domain.Member;
import com.kuit.baemin.domain.MemberStatus;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.INVALID_PASSWORD;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

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

    LocalDateTime now = LocalDateTime.now();
    Member member = new Member();
    member.setEmail(req.getEmail());
    member.setPassword(req.getPassword());
    member.setPhoneNumber(req.getPhoneNumber());
    member.setNickname(req.getNickname());
    member.setStatus(MemberStatus.ACTIVE.toString());
    member.setCreatedAt(now);
    member.setUpdatedAt(now);

    Member saved = memberRepository.save(member);
    return saved.getId();
  }

  public Long login(LoginReq req) {
    Member member = memberRepository
      .findByEmailAndStatus(req.getEmail(), MemberStatus.ACTIVE)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    if (!member.getPassword().equals(req.getPassword())) {
      throw new MemberException(INVALID_PASSWORD);
    }

    return member.getId();
  }

  public MemberRes getMember(Long memberId) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    return MemberRes.from(member);
  }
}
