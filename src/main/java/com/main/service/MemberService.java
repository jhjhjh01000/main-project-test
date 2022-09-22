package com.main.service;

import com.main.domain.member.Member;
import com.main.exception.BusinessLogicException;
import com.main.exception.ExceptionCode;
import com.main.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member createMember(Member member) {

        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    }

    public Member findMember(long memberId) {
        return checkFindMember(memberId);
    }

    public Member updateMember(Member member) {

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        Member findMember = checkFindMember(member.getMemberId());
        Optional.ofNullable(member.getEmail())
            .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(member.getPassword())
            .ifPresent(password -> findMember.setPassword(password));
        return memberRepository.save(findMember);
    }

    private void verifyExistsEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        if (member != null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

    public Member checkFindMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(
            () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

//    public void deleteMember(long id) {
//
//        String logResult = null;
//        System.out.println(logResult.toUpperCase());
//    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
