package com.main.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MemberPatchDto {

    private long memberId;
    private String email;
    private String password;
    private String username;
    private String userId;
    private String role;

    public MemberPatchDto(long memberId, String email, String password, String username,
        String userId,
        String role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }
}