package com.kuit.baemin.service;

import com.kuit.baemin.domain.Member;
import com.kuit.baemin.domain.Order;
import com.kuit.baemin.domain.Store;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final StoreRepository storeRepository;

  public List<OrderRes> getOrders(Long memberId) {
    memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    return orderRepository.findByMemberId(memberId)
      .stream()
      .map(OrderRes::from)
      .toList();
  }

  @Transactional
  public Long createOrder(Long memberId, OrderReq req) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    Store store = storeRepository.findById(req.getStoreId())
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

    LocalDateTime now = LocalDateTime.now();
    Order order = new Order();
    order.setUser(member);
    order.setStore(store);
    order.setRequest(req.getRequest());
    order.setReceiveWay(req.getReceiveWay());
    order.setTotalPrice(req.getTotalPrice());
    order.setEstimateTime(req.getEstimateTime());
    order.setStatus("PENDING");
    order.setCreatedAt(now);
    order.setUpdatedAt(now);

    Order saved = orderRepository.save(order);
    return saved.getId();
  }

  public OrderRes getOrder(Long memberId, Long orderId) {
    Order order = orderRepository.findByIdAndMemberId(orderId, memberId)
      .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));
    return OrderRes.from(order);
  }
}
