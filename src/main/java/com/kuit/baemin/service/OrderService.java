package com.kuit.baemin.service;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.order.OrderItem;
import com.kuit.baemin.domain.order.OrderItemStatus;
import com.kuit.baemin.domain.order.OrderProgressStatus;
import com.kuit.baemin.domain.order.OrderStatus;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.request.OrderItemReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public Long create(OrderCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        if (!restaurant.isOrderable()) {
            throw new GeneralException(ErrorStatus.RESTAURANT_NOT_ACTIVE);
        }

        Map<Long, Menu> menus = menuRepository.findAllById(
                        req.getItems().stream().map(OrderItemReq::getMenuId).toList())
                .stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));

        Order order = Order.builder()
                .member(member)
                .restaurant(restaurant)
                .deliveryRoadAddress(req.getDeliveryRoadAddress())
                .deliveryDetailAddress(req.getDeliveryDetailAddress())
                .totalPrice(0)
                .orderStatus(OrderProgressStatus.PLACED)
                .status(OrderStatus.ACTIVE)
                .build();

        for (OrderItemReq itemReq : req.getItems()) {
            Menu menu = menus.get(itemReq.getMenuId());
            if (menu == null || !menu.isOrderable()) {
                throw new GeneralException(ErrorStatus.MENU_NOT_FOUND);
            }
            if (!menu.getRestaurant().getId().equals(restaurant.getId())) {
                throw new GeneralException(ErrorStatus.MENU_NOT_IN_RESTAURANT);
            }

            OrderItem item = OrderItem.builder()
                    .menu(menu)
                    .quantity(itemReq.getQuantity())
                    .priceAtOrder(menu.getPrice())
                    .status(OrderItemStatus.ACTIVE)
                    .build();
            order.addItem(item);
        }

        if (order.itemsSubtotal() < restaurant.getMinOrderPrice()) {
            throw new GeneralException(ErrorStatus.ORDER_BELOW_MIN_PRICE);
        }
        order.finalizeTotal(restaurant.getDeliveryFee());

        return orderRepository.save(order).getId();
    }

    public PageRes<OrderRes> listByMember(Long memberId, Pageable pageable) {
        if (!memberRepository.existsById(memberId)) {
            throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
        }
        return PageRes.of(
                orderRepository.findAllByMember_IdAndStatus(memberId, OrderStatus.ACTIVE, pageable),
                OrderRes::from
        );
    }

    @Transactional
    public void cancel(Long orderId, Long memberId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ORDER_NOT_FOUND));

        if (!order.isOwnedBy(memberId)) {
            throw new GeneralException(ErrorStatus.ORDER_FORBIDDEN);
        }

        order.cancel();
    }
}
