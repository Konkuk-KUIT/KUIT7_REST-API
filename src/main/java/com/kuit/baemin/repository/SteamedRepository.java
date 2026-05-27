package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Steamed;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.response.SliceRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SteamedRepository extends JpaRepository<Steamed, Long> {

    @EntityGraph(attributePaths = {"store"})
    Slice<Steamed> findByMemberOrderByCreatedAtDesc(Member member, Pageable page);

    Optional<Steamed> findByMemberAndStore(Member member, Restaurant restaurant);

    Boolean existsByStore(Restaurant restaurant);
}
