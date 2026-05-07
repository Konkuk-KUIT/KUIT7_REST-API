package com.kuit.baemin;

import com.kuit.baemin.domain.*;
import com.kuit.baemin.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 서버 시작 시 더미 데이터를 자동으로 DB에 넣어줍니다.
 * application.yml 의 ddl-auto: create 설정과 함께 사용하세요.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
//        log.info("=== 더미 데이터 초기화 시작 ===");
//
//        // ── 회원 3명 ──
//        Member member1 = Member.create("kuit@naver.com", "password1!", "010-1234-5678", "쿠잇유저");
//        Member member2 = Member.create("test@gmail.com", "password2!", "010-9876-5432", "테스트유저");
//        Member member3 = Member.create("admin@kuit.com", "admin1234!", "010-1111-2222", "관리자");
//
//        em.persist(member1);
//        em.persist(member2);
//        em.persist(member3);
//
//        // ── 주소 ──
//        Address addr1 = Address.create(member1, "서울시 강남구 테헤란로 123", AddressType.HOME);
//        Address addr2 = Address.create(member1, "서울시 서초구 반포대로 456", AddressType.COMPANY);
//        Address addr3 = Address.create(member2, "서울시 마포구 홍대입구로 789", AddressType.HOME);
//
//        em.persist(addr1);
//        em.persist(addr2);
//        em.persist(addr3);
//
//        // ── 음식점 3개 ──
//        Restaurant r1 = Restaurant.create("맛있는 치킨집", "서울시 강남구 역삼동 11-1");
//        Restaurant r2 = Restaurant.create("행복한 피자", "서울시 강남구 논현동 22-2");
//        Restaurant r3 = Restaurant.create("신선한 초밥", "서울시 서초구 방배동 33-3");
//
//        em.persist(r1);
//        em.persist(r2);
//        em.persist(r3);
//
//        // ── 메뉴 ──
//        em.persist(Menu.create(r1, "후라이드 치킨", 18000));
//        em.persist(Menu.create(r1, "양념 치킨", 19000));
//        em.persist(Menu.create(r1, "반반 치킨", 19000));
//        em.persist(Menu.create(r1, "치킨 무", 1000));
//
//        em.persist(Menu.create(r2, "페퍼로니 피자", 22000));
//        em.persist(Menu.create(r2, "포테이토 피자", 21000));
//        em.persist(Menu.create(r2, "콤비네이션 피자", 24000));
//
//        em.persist(Menu.create(r3, "연어 초밥 세트", 28000));
//        em.persist(Menu.create(r3, "참치 초밥 세트", 25000));
//        em.persist(Menu.create(r3, "새우 초밥 세트", 23000));
//
//        log.info("=== 더미 데이터 초기화 완료 ===");
//        log.info("회원 3명, 주소 3개, 음식점 3개, 메뉴 10개 생성됨");
    }
}
