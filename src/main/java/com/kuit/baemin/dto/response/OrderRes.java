package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Order.Order;
import com.kuit.baemin.domain.Order.OrderStatus;
import com.kuit.baemin.domain.OrderMenu.OrderMenu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderRes {

    private Long id;
    private String userName;
    private String restaurantName;
    private String address;
    private List<OrderMenuRes> menus;

    public static OrderRes from(Order order, List<OrderMenu> orderMenus) {

        String address = order.getAddress().getAddress();
        String detail = order.getAddress().getAddressDetail();
        String fullAddress = (detail != null) ? (address + " " + detail) : address;

        return OrderRes.builder()
                .id(order.getId())
                .userName(order.getUser().getName())
                .restaurantName(order.getRestaurant().getName())
                .address(fullAddress)
                .menus(orderMenus.stream()
                        .map(OrderMenuRes::from)
                        .toList())
                .build();
    }

}
