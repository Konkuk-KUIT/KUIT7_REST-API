package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.MenuOption;
import com.kuit.baemin.domain.menu.SelectionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuOptionRes {

    private Long id;
    private String optionGroup;
    private String optionName;
    private Integer additionalPrice;
    private Boolean isRequired;
    private SelectionType selectionType;

    public static MenuOptionRes from(MenuOption option) {
        return MenuOptionRes.builder()
                .id(option.getId())
                .optionGroup(option.getOptionGroup())
                .optionName(option.getOptionName())
                .additionalPrice(option.getAdditionalPrice())
                .isRequired(option.getIsRequired())
                .selectionType(option.getSelectionType())
                .build();
    }
}
