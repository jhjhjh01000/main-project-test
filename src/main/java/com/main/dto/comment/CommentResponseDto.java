package com.main.dto.comment;

import com.main.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commendId;
    private Long imageId;
    private String content;
    private Member member;

}
