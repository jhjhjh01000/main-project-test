package com.main.dto.auth;

import java.time.LocalDateTime;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private long memberId;
    private String email;

    private String password;
    private String username;
    private String name;
    private String role;

    private String profileImageUrl;

    private LocalDateTime creation_date;

    private LocalDateTime last_edit_date;
}