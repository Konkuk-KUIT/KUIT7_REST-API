package com.kuit.baemin.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "Bad request."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "Authentication is required."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "Access is denied."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "Resource not found."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "Internal server error."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "Member not found."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409", "Email is already in use."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401", "Invalid password."),

    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "ADDRESS404", "Address not found."),
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT404", "Restaurant not found."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER404", "Order not found."),
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "MENU404", "Menu not found."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404", "Review not found.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
