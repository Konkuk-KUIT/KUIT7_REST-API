package com.kuit.baemin.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // Common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "\uC798\uBABB\uB41C \uC694\uCCAD\uC785\uB2C8\uB2E4."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "\uC778\uC99D\uC774 \uD544\uC694\uD569\uB2C8\uB2E4."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "\uC694\uCCAD\uD55C \uB9AC\uC18C\uC2A4\uB97C \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "\uC11C\uBC84 \uC624\uB958\uAC00 \uBC1C\uC0DD\uD588\uC2B5\uB2C8\uB2E4."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "\uC874\uC7AC\uD558\uC9C0 \uC54A\uB294 \uD68C\uC6D0\uC785\uB2C8\uB2E4."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409", "\uC774\uBBF8 \uC0AC\uC6A9 \uC911\uC778 \uC774\uBA54\uC77C\uC785\uB2C8\uB2E4."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401", "\uBE44\uBC00\uBC88\uD638\uAC00 \uC77C\uCE58\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4."),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "\uC874\uC7AC\uD558\uC9C0 \uC54A\uB294 \uAC00\uAC8C\uC785\uB2C8\uB2E4."),

    // Cart
    MENU_NOT_IN_STORE(HttpStatus.BAD_REQUEST, "CART400", "\uD574\uB2F9 \uAC00\uAC8C\uC5D0 \uC18D\uD55C \uBA54\uB274\uAC00 \uC544\uB2D9\uB2C8\uB2E4."),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "CART404", "\uC874\uC7AC\uD558\uC9C0 \uC54A\uB294 \uC7A5\uBC14\uAD6C\uB2C8 \uD56D\uBAA9\uC785\uB2C8\uB2E4."),

    // TODO: Add mission-specific error codes.
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
