package com.kuit.baemin.domain.Order;

public enum OrderStatus {
    PENDING,        // 주문 접수 대기
    ACCEPTED,       // 주문 수락
    COOKING,        // 조리 중
    DELIVERing,     // 배달 중
    DELIVERED,      // 배달 완료
    CANCELLED       // 주문 취소
}
