package com.main.config.auth;

import com.main.domain.image.Image;
import com.main.domain.member.Member;
import com.main.dto.auth.MemberRegisterDto;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class PrincipalDetails implements PrincipalDetails1 {

    private Member member;
    private MemberRegisterDto memberRegisterDto;
    private Image image;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        member.getRoleList().forEach(n -> {
            authorities.add(() -> n);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
    @Override
    public String getEmail() {
        return member.getUsername();
    }

    @Override
    public Long getSnsId() {
        return image.getSnsId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
