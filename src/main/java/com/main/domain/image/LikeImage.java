package com.main.domain.image;

import com.main.domain.member.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.userdetails.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class LikeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;  // 하나의 사진에 여러개의 좋아요 가능 1:N

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;


    @Column(nullable = false)
    private boolean status; // true = 좋아요, false = 좋아요 취소


    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


    public LikeImage(Image image, Member member ) {
        this.image = image;
        this.member = member;
        this.status = true;
    }

    /**
     * 좋아요 취소 API
     */

    public void unLikeImage(Image image) {
        this.status = false;
        image.setLiked(image.getLiked() - 1);
    }



    //LikeBoard 엔터티는 사용자가 글에 좋아요를 누르면 ,사용자 - 게시글 간 테이블이 생김.
    //그리고 boolean 값으로 좋아요를 눌렀는지 안 눌렀는지 체크.
    //@ManyToOne 어노테이션으로 Member 테이블로 단방향 매핑을
    //@OnDelete(action = OnDeleteAction.CASCADE) 어노테이션으로 인해 ,유저가 지워지면, 사용자 - 게시글의 연관관계인 LikeImage 릴레이션도 삭제가 됩니다.

}
