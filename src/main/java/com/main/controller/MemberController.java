package com.main.controller;

import com.main.domain.member.Member;
import com.main.dto.auth.MemberPatchDto;
import com.main.dto.auth.MemberRegisterDto;
import com.main.dto.auth.MemberResponseDto;
import com.main.mapper.MemberMapper;
import com.main.oauth.PrincipalDetails;
import com.main.service.MemberService;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Slf4j

public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper,
        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberRegisterDto memberRegisterDto) {
        memberRegisterDto.setPassword(
            bCryptPasswordEncoder.encode(memberRegisterDto.getPassword()));
        memberRegisterDto.setRole("ROLE_USER");
        Member member = mapper.memberPostDtoToMember(memberRegisterDto);

        Member response = memberService.createMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
            HttpStatus.CREATED);
    }

    @RequestMapping("/api/users")

    @PatchMapping("/{userId}")
    public ResponseEntity patchMember(@AuthenticationPrincipal PrincipalDetails principalDetails,
        @PathVariable("userId") @Positive Long memberId,
        @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member response =
            memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));


        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
            HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getMember(
        @PathVariable("userId") @Positive long memberId) {
        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
            HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteMember(
        @PathVariable("userId") @Positive long memberId) {
        System.out.println("# delete member");
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}