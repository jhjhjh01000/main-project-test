package com.main.dto.auth;


import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberPatchDto {

    private Long memberId;
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$",
        message = "비밀번호는 6~20 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;
    private String username;
    private String name;
    private String role;




    private String profileImageUrl;


}
