package com.kuit.baemin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressDelRes {
    private Long addressId;
    private String status;
    private String message;
}