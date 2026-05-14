package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.domain.cart.CartItem;
import com.kuit.baemin.domain.cart.CartItemStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.dto.request.CartItemCreateReq;
import com.kuit.baemin.dto.request.CartItemUpdateReq;
import com.kuit.baemin.dto.response.CartItemRes;
import com.kuit.baemin.exception.CartException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.CartItemRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public CartItemRes createCartItem(CartItemCreateReq req) {
        Member member = memberRepository.findByIdAndStatus(req.getMemberId(), MemberStatus.ACTIVE)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findByIdAndStatus(req.getStoreId(), RestaurantStatus.ACTIVE)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
        Menu menu = menuRepository.findByIdAndRestaurantAndStatus(req.getMenuId(), restaurant, MenuStatus.ACTIVE)
                .orElseThrow(() -> new CartException(ErrorStatus.MENU_NOT_IN_STORE));

        CartItem cartItem = CartItem.builder()
                .member(member)
                .restaurant(restaurant)
                .menu(menu)
                .quantity(req.getQuantity())
                .status(CartItemStatus.ACTIVE)
                .build();

        return CartItemRes.from(cartItemRepository.save(cartItem));
    }

    @Transactional
    public CartItemRes updateCartItem(Long cartId, CartItemUpdateReq req) {
        CartItem cartItem = cartItemRepository.findByIdAndStatus(cartId, CartItemStatus.ACTIVE)
                .orElseThrow(() -> new CartException(ErrorStatus.CART_ITEM_NOT_FOUND));

        cartItem.updateQuantity(req.getQuantity());
        return CartItemRes.from(cartItem);
    }

    @Transactional
    public void deleteCartItem(Long cartId) {
        CartItem cartItem = cartItemRepository.findByIdAndStatus(cartId, CartItemStatus.ACTIVE)
                .orElseThrow(() -> new CartException(ErrorStatus.CART_ITEM_NOT_FOUND));

        cartItem.delete();
    }
}
