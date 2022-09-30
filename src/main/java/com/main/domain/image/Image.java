package com.main.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.main.domain.member.Member;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {

    //필요한 기능 = 이미지 좋아요, 댓글
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String caption;  //글 올릴떄 올라가는 글

    private String postImageUrl; // 사진을 전송받아서 서버의 특정폴더에 저장할것, DB에 저장된 경로 INSERT,그래서 애는 경로만 갖고있다

    private String username;

    @JsonIgnoreProperties({"member"})   //json으로 파싱할떄 메세지 컨버터야 user는 하지마 (Image에 있는 user를 무시하고 파싱)
    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Image> images;

    @JoinColumn(name = "memberId")
    @ManyToOne
    private Member member; // 한명의 유저는 여러 이미지를 업로드할 수 있다.

}
