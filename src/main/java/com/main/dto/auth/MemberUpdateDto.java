package com.main.dto.auth;

import com.main.domain.member.Member;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class MemberUpdateDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Member toEntity(){
        return Member.builder()
            .username(username)
            .password(password)
            .build();
    }
}