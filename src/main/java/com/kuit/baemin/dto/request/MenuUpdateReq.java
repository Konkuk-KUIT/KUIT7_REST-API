package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class MenuUpdateReq {

    @Size(max = 100, message = "메뉴명은 100자 이하여야 합니다.")
    private String name;

    @Size(max = 500, message = "메뉴 설명은 500자 이하여야 합니다.")
    private String description;

    @DecimalMin(value = "0.00", message = "메뉴 가격은 0원 이상이어야 합니다.")
    private BigDecimal price;

    @Size(max = 2000, message = "이미지 URL은 2000자 이하여야 합니다.")
    private String imageUrl;

    private Boolean isSoldOut;

    private Integer displayOrder;
}
