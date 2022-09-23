package com.main.domain.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // DB에 테이블 생성
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스 따라감
    private Long memberId;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String userId;

    @Column
    private String website;

    @Column
    private String bio;

    @Column
    private String phone;

    @Column
    private String profileImageUrl;

    @Column
    private String role;

//    @Column
//    private LocalDateTime createDate;
    @Column
    private LocalDateTime creation_date = LocalDateTime.now();

    @Column
    private LocalDateTime last_edit_date = LocalDateTime.now();


//    @PrePersist // DB에 INSERT 되기 직전에 실행
//    public void createdDate() {
//        this.last_edit_date = LocalDateTime.now();
//    }

    public List<String> getRoleList() {
        if (this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }


}
