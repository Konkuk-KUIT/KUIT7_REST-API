package com.kuit.baemin.repository;

import com.kuit.baemin.domain.cart.Cart;
import com.kuit.baemin.domain.cart.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByMemberIdAndStatus(Long userId, CartStatus status);
}