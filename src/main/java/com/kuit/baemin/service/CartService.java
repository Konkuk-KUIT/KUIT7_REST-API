package com.kuit.baemin.service;

import com.kuit.baemin.domain.Cart.Cart;
import com.kuit.baemin.domain.Store.*;
import com.kuit.baemin.domain.User.User;
import com.kuit.baemin.dto.request.CartCreateReq;
import com.kuit.baemin.dto.request.CartUpdateReq;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final MenuDetailRepository menuDetailRepository;

    // 6. 장바구니 담기
    public Long addToCart(@Valid CartCreateReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        Menu menu = menuRepository.findById(req.getMenuId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_NOT_FOUND));
        MenuDetail detail = menuDetailRepository.findById(req.getMenuDetailId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_DETAIL_NOT_FOUND));

        Cart cart = Cart.builder()
                .user(user).store(store).menu(menu).menuDetail(detail)
                .payMethod(req.getPayMethod())
                .request(req.getRequest())
                .storeRequest(req.getStoreRequest())
                .build();

        return cartRepository.save(cart).getCartId();
    }

    // 7. 장바구니 수정 (요청사항 등 변경)
    public Long updateCart(Long cartId, @Valid CartUpdateReq req) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CART_NOT_FOUND));

        // Dirty Checking을 통한 수정
        cart.updateRequest(req.getRequest(), req.getStoreRequest());

        return cart.getCartId();
    }

    // 8. 장바구니 품목 삭제
    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new GeneralException(ErrorStatus.CART_NOT_FOUND);
        }
        cartRepository.deleteById(cartId);
    }
}
