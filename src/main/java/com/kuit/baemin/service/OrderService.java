package com.kuit.baemin.service;


import com.kuit.baemin.domain.Menu.Menu;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.OrderDetail;
import com.kuit.baemin.domain.order.OrderStatus;
import com.kuit.baemin.domain.order.Orders;
import com.kuit.baemin.dto.request.OrderMenuReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.MenuException;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    @Transactional
    public Long createOrder( OrderReq req){
        Member member = memberRepository.findById(req.getUserId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));

        int calculatedTotalPrice = 0;
        for (OrderMenuReq menuReq : req.getOrderMenus()) {
            Menu menu = menuRepository.findById(menuReq.getMenuId())
                    .orElseThrow(() -> new MenuException(ErrorStatus.MENU_NOT_FOUND));

            calculatedTotalPrice += (menu.getPrice() * menuReq.getQuantity());
        }

        // 부모 테이블에 저장(Orders)
        Orders order = Orders.builder()
                .member(member)
                .store(store)
                .totalPrice(calculatedTotalPrice)
                .status(OrderStatus.배달완료)
                .build();
        orderRepository.save(order);

        //자식 테이블에 저장(OrderDetail)
        for (OrderMenuReq menuReq : req.getOrderMenus()) {
            Menu menu = menuRepository.findById(menuReq.getMenuId())
                    .orElseThrow(()-> new MenuException(ErrorStatus.MENU_NOT_FOUND));

            OrderDetail orderDetail = OrderDetail.builder()
                    .orders(order)
                    .menu(menu)
                    .quantity(menuReq.getQuantity())
                    .unitPrice(menu.getPrice())
                    .build();
            orderDetailRepository.save(orderDetail);
        }

        return order.getId();

    }

}
