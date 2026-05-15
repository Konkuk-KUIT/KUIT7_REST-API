package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // 특정 유저의 장바구니 목록 전체 삭제 (주문 완료 후 사용)
    void deleteAllByUser_UserId(Long userId);
}
