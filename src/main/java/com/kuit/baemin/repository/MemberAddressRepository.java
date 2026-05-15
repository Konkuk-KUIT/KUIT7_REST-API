package com.kuit.baemin.repository;

import com.kuit.baemin.domain.member_address.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {
    Optional<MemberAddress> findByMemberIdAndAddressId(Long memberId, Long addressId);
}
