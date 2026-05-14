package com.kuit.baemin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor // 생성자 private 에러 안 나게 public으로 확실하게 열어둡니다!
public class OrderCancelRes {
    private Long orderId;
    private String status;
    private String message;
}