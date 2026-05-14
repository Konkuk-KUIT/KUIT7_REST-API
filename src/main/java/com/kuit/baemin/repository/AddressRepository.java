package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

  // 회원 ID로 주소 조회
  List<Address> findByMemberId(Long memberId);

  // 주소 ID와 회원 ID로 조회
  Optional<Address> findByIdAndMemberId(Long id, Long memberId);
}
