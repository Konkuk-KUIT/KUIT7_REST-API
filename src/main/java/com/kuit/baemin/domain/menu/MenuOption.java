package com.kuit.baemin.domain.menu;

import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "menu_options")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 옵션은 반드시 한 메뉴에 속함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false, length = 100)
    private String optionGroup;

    @Column(nullable = false, length = 100)
    private String optionName;

    @Column(nullable = false)
    private Integer additionalPrice;

    @Column(nullable = false)
    private Boolean isRequired;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SelectionType selectionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MenuOptionStatus status;
}