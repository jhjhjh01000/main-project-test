package com.main.mapper;

import com.main.domain.comment.Comment;
import com.main.domain.image.Image;
import com.main.domain.member.Member;
import com.main.dto.image.ImageResponseDto;
import com.main.dto.auth.MemberPatchDto;
import com.main.dto.auth.MemberRegisterDto;
import com.main.dto.auth.MemberResponseDto;
import com.main.dto.comment.CommentResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberRegisterDto memberRegisterDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

    List<ImageResponseDto> pageResponseDtos(List<Image> images);

    List<CommentResponseDto> commentsToCommentResponses(List<Comment> comments);

}
