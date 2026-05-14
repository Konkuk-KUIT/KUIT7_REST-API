package com.kuit.baemin.service;

import com.kuit.baemin.domain.Review;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.Orders;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.dto.response.CreateReviewRes;
import com.kuit.baemin.dto.response.StoreRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.ReviewRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;



    public StoreRes getStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new StoreException(STORE_NOT_FOUND));

        return StoreRes.from(store);
    }

    @Transactional
    public CreateReviewRes createReview(Long storeId , ReviewReq req){
        Member member = memberRepository.findById(req.getUserId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
        Orders order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new OrderException(ErrorStatus.ORDER_NOT_FOUND));

        /**
         *
         * 예외 처리 추가하기**/


        Review review = Review.builder()
                .member(member)
                .store(store)
                .order(order)
                .rating(req.getRating())
                .content(req.getContent())
                .build();
        reviewRepository.save(review);

        store.updateAverageRating(req.getRating());

        return CreateReviewRes.builder()
                .reviewId(review.getId())
                .build();

    }

}
