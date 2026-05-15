package com.kuit.baemin.repository;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByMemberIdAndStatus(Long memberId, AddressStatus status);

    List<Address> findAllByMemberIdAndIsDefaultTrueAndStatus(Long memberId, AddressStatus status);
}
