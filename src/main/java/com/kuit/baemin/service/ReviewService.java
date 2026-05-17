package com.kuit.baemin.service;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.Restaurant.Store;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.domain.review.ReviewStatus;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.ReviewRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;

    public Long createReview(ReviewReq req) {
        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Member member = memberRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Menu menu = null;
        if (req.getMenuId() != null) {
            menu = menuRepository.findById(req.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
        }

        Review review = Review.builder()
                .store(store)
                .member(member)
                .menu(menu)
                .rating(req.getRating())
                .content(req.getContent())
                .reviewPictureUrl(req.getReviewPictureUrl())
                .status(ReviewStatus.ACTIVE)
                .build();

        return reviewRepository.save(review).getId();
    }
}