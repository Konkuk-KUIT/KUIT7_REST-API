package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Favorite;
import com.kuit.baemin.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

    List<Favorite> findAllByMemberAndStatus(Member member, String status);

}
