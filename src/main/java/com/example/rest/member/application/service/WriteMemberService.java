package com.example.rest.member.application.service;

import com.example.rest.member.application.repository.SpringDataJpaMemberRepository;
import com.example.rest.member.domain.Member;
import com.example.rest.member.web.MemberDTO;
import com.example.rest.member.web.command.ChangePasswordCommand;
import com.example.rest.member.web.command.RegisterMemberCommand;
import com.example.rest.utils.SHA256Util;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        var hashedPassword = SHA256Util.encryptSHA256(register.getPassword());
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
    public void changePassword(ChangePasswordCommand changePassword) {
        Member member = readMemberService.findByMemberId(changePassword.getMemberId());
        member.validatePasswordChange(changePassword.getNewPassword());
        member.changePassword(changePassword.getNewPassword());
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