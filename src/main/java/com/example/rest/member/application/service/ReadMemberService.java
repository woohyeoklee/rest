package com.example.rest.member.application.service;

import com.example.rest.member.adapter.out.MemberJpaEntity;

import com.example.rest.member.application.port.in.command.LoginMemberCommand;
import com.example.rest.member.application.port.in.usecase.ReadMemberUseCase;
import com.example.rest.member.domain.MemberDTO;
import com.example.rest.member.adapter.in.request.LoginMemberRequest;
import com.example.rest.member.application.port.out.FindMemberPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReadMemberService implements ReadMemberUseCase {

    private final FindMemberPort findMemberPort;

    // 회원 조회
    @Override
    public MemberDTO findByMemberId(String memberId) {
        MemberJpaEntity memberJpaEntity = findMemberPort.findByMemberId(memberId);
        return mapToDTO(memberJpaEntity);
    }

    //로그인 TODO: 스프링 시큐리티 적용.
    public MemberDTO login(LoginMemberCommand loginCommand) {
        MemberJpaEntity memberJpaEntity = findMemberPort.findByMemberId(loginCommand.getMemberId());
        if (memberJpaEntity.getPassword().equals(loginCommand.getPassword())) {
            return mapToDTO(memberJpaEntity);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }
    public MemberDTO mapToDTO(MemberJpaEntity memberJpaEntity) {
        return new MemberDTO(
                memberJpaEntity.getMemberId(),
                memberJpaEntity.getName(),
                memberJpaEntity.getEmail()
        );
    }
}