package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
  List<Address> findByMemberId(Long memberId);
  Optional<Address> findByIdAndMemberId(Long id, Long memberId);
}
