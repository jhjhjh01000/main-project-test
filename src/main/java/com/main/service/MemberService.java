package com.main.service;

import com.main.domain.member.Member;
import com.main.exception.BusinessLogicException;
import com.main.exception.ExceptionCode;
import com.main.oauth.PrincipalDetails;
import com.main.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
//@Transactional
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



        //레포지토리에서 회원가입할때의 멤버아이디값을 가져오고 마이페이지 회원정보 수정의 엔드포인트로 받는 멤버아이디값을
        // 로그인 했을때의 멤버아디값과 비교해서 동일하면 패치가능하게
        Member findMember = checkFindMember(member.getMemberId());
        Optional<Member> repo = memberRepository.findById(memberRepository.count());
        if (findMember.getMemberId() != repo.get().getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCH);
        }
            Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
            Optional.ofNullable(member.getPassword())
                .ifPresent(
                    password -> findMember.setPassword(bCryptPasswordEncoder.encode(password)));

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


    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}

