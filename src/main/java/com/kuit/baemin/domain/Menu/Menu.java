package com.kuit.baemin.domain.Menu;

import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(name = "menu_name", nullable = false, length = 200)
    private String menuName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 100)
    private String status;


    // 외래키(store_id)를 통해 어떤 가게의 메뉴인지 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

}