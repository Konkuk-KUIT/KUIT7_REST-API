package com.kuit.baemin.repository;

import com.kuit.baemin.domain.member.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}