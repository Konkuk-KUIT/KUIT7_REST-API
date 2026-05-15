package com.kuit.baemin.repository;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Page<Member> findAll(Pageable pageable);

    // 이메일로 조회 (로그인, 중복 확인)
    Optional<Member> findByEmail(String email);

    // 활성 회원만 이메일로 조회
    Optional<Member> findByEmailAndStatus(String email, MemberStatus status);

    // 이메일 중복 확인
    boolean existsByEmail(String email);
}
