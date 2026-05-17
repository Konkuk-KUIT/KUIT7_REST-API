package com.kuit.baemin.service;

import com.kuit.baemin.domain.cart.Cart;
import com.kuit.baemin.domain.cart.CartStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.Restaurant.Store;
import com.kuit.baemin.dto.request.CartReq;
import com.kuit.baemin.repository.CartRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    /**
     * 6. 장바구니 담기 (POST)
     */
    @Transactional
    public Long addToCart(CartReq req) {
        Member member = memberRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Menu menu = menuRepository.findById(req.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));

        Cart cart = Cart.builder()
                .member(member)
                .store(store)
                .menu(menu)
                .quantity(req.getQuantity())
                .status(CartStatus.ACTIVE)
                .build();

        return cartRepository.save(cart).getId();
    }

    /**
     * 7. 장바구니 항목 삭제 (DELETE)
     */
    @Transactional
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니 내역입니다."));

        cartRepository.delete(cart);
    }

    /**
     * 8. 내 장바구니 목록 조회 (GET)
     */
    public List<com.kuit.baemin.dto.response.CartListRes> getCartList(Long userId) {
        List<Cart> carts = cartRepository.findByMemberIdAndStatus(userId, CartStatus.ACTIVE);

        return carts.stream()
                .map(com.kuit.baemin.dto.response.CartListRes::from)
                .collect(java.util.stream.Collectors.toList());
    }
}