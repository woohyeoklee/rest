package com.example.rest.member.application.service;

import com.example.rest.member.adapter.out.SpringDataJpaMemberRepository;
import com.example.rest.member.domain.Member;
import com.example.rest.member.adapter.in.command.ChangePasswordCommand;
import com.example.rest.member.adapter.in.command.RegisterMemberCommand;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class WriteMemberService {

    private final SpringDataJpaMemberRepository jpaMemberRepository;
    private final ReadMemberService readMemberService;


    //회원가입
    public void register(@Valid RegisterMemberCommand register) {
        if (jpaMemberRepository.existsByMemberId(register.getMemberId())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        var hashedPassword = (register.getPassword());
        Member member = Member.builder()
                .memberId(register.getMemberId())
                .password(hashedPassword)
                .name(register.getName())
                .email(register.getEmail())
                .role(Member.Role.USER)
                .build();
        jpaMemberRepository.save(member);
    }

    // 비밀번호 변경
    @Transactional
    public void changePassword(ChangePasswordCommand command) {
        Member member = readMemberService.findByMemberId(command.getMemberId());
        var newHashedPassword = (command.getNewPassword());
        member.changePassword(newHashedPassword);
        jpaMemberRepository.save(member);
    }

    // 회원 탈퇴
    public void deleteMember(String memberId) {
        Member member = readMemberService.findByMemberId(memberId);
        if (member != null) {
            jpaMemberRepository.delete(member);
        } else {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }
}