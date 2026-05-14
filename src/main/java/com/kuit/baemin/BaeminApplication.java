package com.kuit.baemin;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // BaseEntity createdAt, updatedAt 자동 주입
@SpringBootApplication
public class BaeminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaeminApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            RestaurantRepository restaurantRepository,
            MenuRepository menuRepository) {
        return args -> {

            // 데이터가 비어있을 때만 초기화 진행 (중복 저장 방지)
            if (restaurantRepository.count() == 0) {

                // 1. BBQ 치킨 음식점 등록
                Restaurant bbq = Restaurant.builder()
                        .name("BBQ 치킨 광진점")
                        .minOrderPrice(16000)
                        .address("서울시 광진구 화양동")
                        .status(RestaurantStatus.ACTIVE)
                        .build();
                restaurantRepository.save(bbq);

                // BBQ 메뉴 등록
                Menu bbqMenu1 = Menu.builder()
                        .restaurant(bbq)
                        .name("황금올리브치킨")
                        .price(23000)
                        .category("치킨")
                        .status(MenuStatus.ACTIVE)
                        .build();

                Menu bbqMenu2 = Menu.builder()
                        .restaurant(bbq)
                        .name("자메이카 통다리구이")
                        .price(21500)
                        .category("치킨")
                        .status(MenuStatus.ACTIVE)
                        .build();

                menuRepository.save(bbqMenu1);
                menuRepository.save(bbqMenu2);


                // 2. 도미노 피자 음식점 등록
                Restaurant domino = Restaurant.builder()
                        .name("도미노피자 건대점")
                        .minOrderPrice(18000)
                        .address("서울시 광진구 자양동")
                        .status(RestaurantStatus.ACTIVE)
                        .build();
                restaurantRepository.save(domino);

                // 도미노 피자 메뉴 등록
                Menu dominoMenu1 = Menu.builder()
                        .restaurant(domino)
                        .name("포테이토 피자 L")
                        .price(27900)
                        .category("피자")
                        .status(MenuStatus.ACTIVE)
                        .build();

                Menu dominoMenu2 = Menu.builder()
                        .restaurant(domino)
                        .name("블랙앵거스 스테이크 피자 L")
                        .price(35900)
                        .category("피자")
                        .status(MenuStatus.ACTIVE)
                        .build();

                menuRepository.save(dominoMenu1);
                menuRepository.save(dominoMenu2);
            }
        };
    }
}
