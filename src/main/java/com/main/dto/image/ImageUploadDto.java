package com.main.dto.image;

import com.main.domain.image.Image;
import com.main.domain.member.Member;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;

    // postImage가 정확하게 뭐냐면면
    public Image toEntity(String postImageUrl, Member member, String username){
        return Image.builder()
            .caption(caption)
            .username(username)
            .postImageUrl(postImageUrl)
            .member(member)
            .build();
    }
}
