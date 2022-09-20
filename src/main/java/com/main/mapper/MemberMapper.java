package com.main.mapper;

import com.main.domain.member.Member;
import com.main.dto.auth.MemberPatchDto;
import com.main.dto.auth.MemberRegisterDto;
import com.main.dto.auth.MemberResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberRegisterDto memberRegisterDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
