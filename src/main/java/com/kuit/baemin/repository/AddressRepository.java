package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
