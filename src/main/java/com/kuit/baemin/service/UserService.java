package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.INVALID_PASSWORD;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.USER_NOT_FOUND;

import com.kuit.baemin.domain.user.User;
import com.kuit.baemin.domain.user.UserStatus;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.response.UserRes;
import com.kuit.baemin.exception.UserException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long signUp(SignUpReq req) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(req.getEmail())) { //있으면 true 반환
            throw new UserException(ErrorStatus.DUPLICATE_EMAIL);
        }

        User user = User.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .phoneNumber(req.getPhoneNumber())
                .nickname(req.getNickname())
                .status(UserStatus.ACTIVE)
                .build();


        User saved = userRepository.save(user);
        return saved.getId();
    }

    /**
     * 로그인
     */
    public Long login(LoginReq req) {
        User user = userRepository
                .findByEmailAndStatus(req.getEmail(), UserStatus.ACTIVE)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new UserException(INVALID_PASSWORD);
        }

        return user.getId();
    }

    /**
     * 회원 단건 조회
     */
    public UserRes getMember(Long memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        return UserRes.from(user);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        userRepository.delete(user);
    }
}
