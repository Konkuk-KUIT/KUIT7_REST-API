package com.kuit.baemin.domain.Cart;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.Store.Menu;
import com.kuit.baemin.domain.Store.MenuDetail;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.User.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(nullable = false, length = 10)
    private String payMethod;

    @Column(nullable = false, length = 200, columnDefinition = "VARCHAR(200) DEFAULT '문 앞에 두면 가져갈게요(벨, 노크 X)'")
    private String request;

    @Column(nullable = false, length = 200, columnDefinition = "VARCHAR(200) DEFAULT '수저, 포크 X'")
    private String storeRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_detail_id")
    private MenuDetail menuDetail;

    public void updateRequest(String request, String storeRequest) {
        if (request != null) {
            this.request = request;
        }
        if (storeRequest != null) {
            this.storeRequest = storeRequest;
        }
    }
}
