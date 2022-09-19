package com.main.mapper;

import com.main.domain.member.Member;
import com.main.domain.member.Member.MemberBuilder;
import com.main.dto.auth.MemberRegisterDto;
import com.main.dto.auth.MemberResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-18T19:07:38+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberRegisterDto memberRegisterDto) {
        if ( memberRegisterDto == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.email( memberRegisterDto.getEmail() );
        member.password( memberRegisterDto.getPassword() );
        member.role( memberRegisterDto.getRole() );

        return member.build();
    }

    @Override
    public Member memberPatchDtoToMember(MemberRegisterDto memberRegisterDto) {
        if ( memberRegisterDto == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.email( memberRegisterDto.getEmail() );
        member.password( memberRegisterDto.getPassword() );
        member.role( memberRegisterDto.getRole() );

        return member.build();
    }

    @Override
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setEmail( member.getEmail() );
        memberResponseDto.setPassword( member.getPassword() );

        return memberResponseDto;
    }
}
