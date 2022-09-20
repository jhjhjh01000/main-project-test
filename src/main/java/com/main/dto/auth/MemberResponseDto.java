package com.main.dto.auth;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class MemberResponseDto {

    private long memberId;
    private String email;
    private String password;
    private String username;
    private String userId;
    private String role;
    private LocalDateTime createDate;

    public MemberResponseDto(long memberId, String email, String password, String username,
        String userId, String role, LocalDateTime createDate) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userId = userId;
        this.role = role;
        this.createDate = createDate;
    }
}