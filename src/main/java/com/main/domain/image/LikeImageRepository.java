package com.main.domain.image;

import com.main.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeImageRepository extends JpaRepository<LikeImage, Integer> {

    LikeImage findByImageAndMember(Image image, Member member);

    //레포지토리에서는 Board 정보와, User 정보를 바탕으로 LikeBoard 객체를 불러온다.
}
