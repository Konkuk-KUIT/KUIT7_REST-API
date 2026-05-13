package com.kuit.baemin.service;

import com.kuit.baemin.domain.Menu.Menu;
import com.kuit.baemin.domain.Order.Order;
import com.kuit.baemin.domain.Order.OrderStatus;
import com.kuit.baemin.domain.OrderMenu.OrderMenu;
import com.kuit.baemin.domain.OrderMenu.OrderMenuStatus;
import com.kuit.baemin.dto.request.OrderMenuReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.*;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final AddressRepository addressRepository;

    /**
     * 주문 상세 조회 (주문한 모든 메뉴 목록 조회)
     */
    public OrderRes getOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));
        return OrderRes.from(order, orderMenuRepository.findByOrderId(orderId));
    }

    /**
     * 주문 생성 (주문 요청)
     */
    @Transactional
    public Long createOrder(OrderReq req) {

        Order order = Order.builder()
                .status(OrderStatus.PENDING)
                .user(userRepository.findById(req.getUserId())
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND)))
                .restaurant((restaurantRepository.findById(req.getRestaurantId()))
                        .orElseThrow(() -> new RestaurantException(RESTAURANT_NOT_FOUND)))
                .address(addressRepository.findById(req.getAddressId())
                        .orElseThrow(() -> new AddressException(ADDRESS_NOT_FOUND)))
                .build();

        Order saved =  orderRepository.save(order);

        // 주문 메뉴 저장
        for (OrderMenuReq menuReq : req.getMenus()) {
            Menu menu = menuRepository.findById(menuReq.getMenuId())
                    .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

            OrderMenu orderMenu = OrderMenu.builder()
                    .order(saved)
                    .menu(menu)
                    .price(menu.getPrice())
                    .quantity(menuReq.getQuantity())
                    .status(OrderMenuStatus.ACTIVE)
                    .build();

            orderMenuRepository.save(orderMenu);
        }

        return saved.getId();
    }
}
