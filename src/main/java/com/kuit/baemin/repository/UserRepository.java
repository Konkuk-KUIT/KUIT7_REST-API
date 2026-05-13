package com.kuit.baemin.repository;

import com.kuit.baemin.domain.User.User;
import com.kuit.baemin.domain.User.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // 이메일로 조회 (로그인, 중복 확인)
    Optional<User> findByEmail(String email);

    // 활성 회원만 이메일로 조회
    Optional<User> findByEmailAndStatus(String email, UserStatus status);

    // 이메일 중복 확인
    boolean existsByEmail(String email);
}
