package com.main.dto.likes;

import com.main.domain.image.Image;
import com.main.domain.image.Likes;
import com.main.domain.member.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikesResponseDto {

    private Long id;

    private Image image;

    private LocalDateTime createDate;

    private LocalDateTime creation_date;

    private LocalDateTime last_edit_date;
}
