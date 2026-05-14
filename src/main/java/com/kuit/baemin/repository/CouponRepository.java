package com.kuit.baemin.repository;

import com.kuit.baemin.domain.coupon.Coupon;
import com.kuit.baemin.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CouponRepository extends JpaRepository<Coupon, Long> {


    Optional<Coupon> findByIdAndStatus(Long id, String status);
}
