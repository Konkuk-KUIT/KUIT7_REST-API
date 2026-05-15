package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.order.OrderStatus;
import com.kuit.baemin.domain.order_menu.OrderMenu;
import com.kuit.baemin.dto.request.OrderMenuReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderCancelRes;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.MenuException;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.domain.order.OrderStatus.READY;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.BAD_REQUEST;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.FORBIDDEN;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MENU_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.ORDER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public OrderCancelRes cancelOrder(Long orderId, Long memberId) {
        // 1. [404 에러 처리] 존재하지 않는 주문 번호인 경우
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND)); // 본인의 404 예외 클래스로 매핑

        // 2. [403 에러 처리] 남의 주문을 취소하려고 하는 경우
        if (!order.getMember().getId().equals(memberId)) {
            throw new OrderException(FORBIDDEN); // 본인의 403 예외 처리
        }

        // 3. [400 에러 처리] 이미 배달이 시작되어 취소가 불가능한 상태인 경우
        if (order.getStatus().equals(OrderStatus.DELIVER)) {
            throw new OrderException(BAD_REQUEST);
        }

        // 4. 주문 취소 상태 반영 (더티 체킹으로 자동으로 DB에 업데이트 치트키)
        order.changeStatus(OrderStatus.CANCELLED); // 엔티티 내부에 상태 변경 메서드가 있다면 사용

        // 5. 성공 응답 생성 및 리턴
        return new OrderCancelRes(
                order.getId(),
                "CANCELLED",
                "주문이 정상적으로 취소되었습니다."
        );
    }

    @Transactional
    public Long createOrder(OrderReq req) {
        // 1. [예외 처리] 사용자 존재 확인
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        // 2. [예외 처리] 식당 존재 확인
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new RestaurantException(RESTAURANT_NOT_FOUND));

        // 3. 주문 마스터 객체 생성 (우선 빈 주문을 만듭니다)
        Order order = Order.builder()
                .member(member)
                .restaurant(restaurant)
                .request(req.getRequest())
                .status(READY) // 주문 대기 상태
                .build();

        // 4. [핵심] 주문한 메뉴들 하나씩 확인하며 연관 데이터 매핑
        for (OrderMenuReq menuReq : req.getOrderMenus()) {
            // [예외 처리] 명세서 조건: 존재하지 않는 메뉴인 경우 404
            Menu menu = menuRepository.findById(menuReq.getMenuId())
                    .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

            // OrderMenu(주문-메뉴 연결 엔티티) 생성
            OrderMenu orderMenu = OrderMenu.builder()
                    .order(order)
                    .menu(menu)
                    .quantity(menuReq.getQuantity())
                    .build();

            // 주문 객체에 메뉴 추가 (Cascade 설정을 해두면 Order가 저장될 때 같이 저장됩니다)
            order.addOrderMenu(orderMenu);
        }

        // 5. DB에 최종 저장
        Order savedOrder = orderRepository.save(order);

        // 6. 생성된 주문 ID(PK) 반환
        return savedOrder.getId();
    }

    /**
     * 주문 내역 조회
     */
    public OrderRes getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));
        return OrderRes.from(order);
    }
}
