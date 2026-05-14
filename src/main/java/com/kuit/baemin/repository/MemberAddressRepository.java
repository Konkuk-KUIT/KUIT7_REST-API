package com.kuit.baemin.repository;

import com.kuit.baemin.domain.member_address.MemberAddress;
import com.kuit.baemin.domain.member_coupon.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {
    List<MemberAddress> findByMemberId(Long memberId);
}
