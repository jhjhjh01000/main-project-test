package com.main.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    private String content;  // 내용


    private Long imageId;     // 몇번 게시글 쓸것인지

    //toEntity가 필요없다
}
