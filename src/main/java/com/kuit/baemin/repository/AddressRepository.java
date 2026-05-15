package com.kuit.baemin.repository;

import com.kuit.baemin.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByMemberId(Long memberId);
}