package com.kuit.baemin.repository;

import com.kuit.baemin.domain.cart.CartItem;
import com.kuit.baemin.domain.cart.CartItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByIdAndStatus(Long id, CartItemStatus status);
}
