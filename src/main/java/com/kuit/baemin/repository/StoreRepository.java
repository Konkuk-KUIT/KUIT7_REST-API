package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
  List<Store> findByStatus(Store status);
  Optional<Store> findByIdAndStatus(Long id, Store status);
}
