package com.kuit.baemin.service;

import com.kuit.baemin.domain.Order.Order;
import com.kuit.baemin.domain.Order.OrderMenu;
import com.kuit.baemin.domain.Order.OrderStatus;
import com.kuit.baemin.domain.Store.Menu;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.User.User;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.request.OrderMenuReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.StoreRepository;
import com.kuit.baemin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public Long createOrder(OrderCreateReq request) {
        // 1. 주문(Order) 부모 객체 먼저 생성
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow();

        Order order = Order.builder()
                .user(user)
                .store(store)
                .payMethod(request.getPayMethod())
                .request(request.getRequest())
                .storeRequest(request.getStoreRequest())
                .status(OrderStatus.ACTIVE)
                .build();

        // 2. DTO에 담긴 메뉴 리스트를 돌면서 OrderMenu 생성
        for (OrderMenuReq menuReq : request.getOrderMenus()) {
            Menu menu = menuRepository.findById(menuReq.getMenuId()).orElseThrow();

            OrderMenu orderMenu = OrderMenu.builder()
                    .menu(menu)
                    .order(order) // 연관관계 주인 설정
                    .build();

            // Order 엔티티의 리스트에도 추가 (Cascade를 위해)
            order.addOrderMenu(orderMenu);
        }

        // 3. 부모만 저장하면 CascadeType.ALL에 의해 자식(OrderMenu)도 자동 저장
        return orderRepository.save(order).getOrderId();
    }

    public Slice<OrderRes> getUserOrderHistory(Long userId, Pageable pageable) {
        return orderRepository.findByUserIdWithStore(userId, pageable)
                .map(OrderRes::from);
    }
}
