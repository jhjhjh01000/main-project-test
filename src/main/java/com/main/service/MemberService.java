package com.main.service;

import com.main.domain.member.Member;
import com.main.exception.BusinessLogicException;
import com.main.exception.ExceptionCode;
import com.main.repository.MemberRepository;
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



    //verifyExistsEmail 체크후 이메일 존재여부 확인후 멤버레포지토리에 저장
    public Member createMember(Member member) {

        verifyExistsEmail(member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_USER");

        return memberRepository.save(member);
    }

    public Member findMember(long memberId) {
        return checkFindMember(memberId);
    }
    //회원정보 수정
    public Member updateMember(Member member) {



        //레포지토리에서 회원가입할때의 멤버아이디값을 가져오고 마이페이지 회원정보 수정의 엔드포인트로 받는 멤버아이디값을
        // 로그인 했을때의 멤버아디값과 비교해서 동일하면 패치가능하게
        Member findMember = checkFindMember(member.getMemberId());
//        Optional<Member> repo = memberRepository.findById(memberRepository.count());
//        if (findMember.getMemberId() != repo.get().getMemberId()) {
//            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCH);
//        }
            Optional.ofNullable(member.getUsername())
                .ifPresent(username -> findMember.setUsername(username)); //이메일 변경
            Optional.ofNullable(member.getPassword()) // 패스워드 변경
                .ifPresent(
                    password -> findMember.setPassword(bCryptPasswordEncoder.encode(password))); //암호화된 패스워드로 비교

        return memberRepository.save(findMember);
    }
    //멤버 레포지토리에서 이메일이 빈값이 아닐시 예외처리(MEMBER_EXISTS)
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

