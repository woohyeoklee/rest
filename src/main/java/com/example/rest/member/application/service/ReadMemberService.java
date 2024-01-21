package com.example.rest.member.application.service;

import com.example.rest.member.adapter.out.MemberJpaEntity;

import com.example.rest.member.domain.MemberDTO;
import com.example.rest.member.adapter.in.command.LoginMemberRequest;
import com.example.rest.member.application.port.out.FindMemberPort;
import com.example.rest.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadMemberService {

    private final FindMemberPort findMemberPort;

    // 회원 조회
    public MemberDTO findByMemberId(String memberId) {
        MemberJpaEntity memberJpaEntity = findMemberPort.findByMemberId(memberId);
        return mapToDTO(memberJpaEntity);
    }


    //로그인 TODO: 스프링 시큐리티 적용.
    public MemberDTO login(LoginMemberRequest loginMemberRequest) {
        MemberJpaEntity memberJpaEntity = findMemberPort.findByMemberId(loginMemberRequest.getMemberId());

        var hashedPassword = SHA256Util.encryptSHA256(loginMemberRequest.getPassword());

        if (!hashedPassword.equals(memberJpaEntity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return mapToDTO(memberJpaEntity);
    }
    public MemberDTO mapToDTO(MemberJpaEntity memberJpaEntity) {
        return new MemberDTO(
                memberJpaEntity.getMemberId(),
                memberJpaEntity.getName(),
                memberJpaEntity.getEmail()
        );
    }
}