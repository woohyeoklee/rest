package com.example.rest.member.application.service;

import com.example.rest.member.application.repository.SpringDataJpaMemberRepository;
import com.example.rest.member.domain.Member;

import com.example.rest.member.web.MemberDTO;
import com.example.rest.member.web.command.LoginMemberCommand;
import com.example.rest.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadMemberService {

    private final SpringDataJpaMemberRepository jpaMemberRepository;

    // 회원 조회
    public Member findByMemberId(String memberId) {
        return jpaMemberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }


    //로그인 TODO: 스프링 시큐리티 적용.
    public MemberDTO login(LoginMemberCommand loginMemberCommand) {
        Member member = jpaMemberRepository.findByMemberId(loginMemberCommand.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        var hashedPassword = SHA256Util.encryptSHA256(loginMemberCommand.getPassword());

        if (!hashedPassword.equals(member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return Member.mapToDTO(member);
    }
}