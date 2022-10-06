package com.main.dto.image;

import com.main.domain.comment.Comment;
import com.main.domain.image.Image;
import com.main.domain.member.Member;
import com.main.dto.comment.CommentResponseDto;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;

    private Long snsId;

    private Long liked;

    private List<CommentResponseDto> comments;

    // postImage가 정확하게 뭐냐면면
    public Image toEntity(String postImageUrl, Member member, String username, Long snsId, Long liked){
        return Image.builder()
            .caption(caption)
            .username(username)
            .postImageUrl(postImageUrl)
            .snsId(snsId)
            .liked(liked)
            .member(member)
            .build();
    }
}
