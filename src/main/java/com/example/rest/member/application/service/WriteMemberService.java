package com.example.rest.member.application.service;

import com.example.rest.member.domain.MemberDTO;
import com.example.rest.member.adapter.out.MemberJpaEntity;


import com.example.rest.member.application.port.in.command.UpdateMemberCommand;
import com.example.rest.member.application.port.in.command.RegisterMemberCommand;
import com.example.rest.member.application.port.in.usecase.WriteMemberUseCase;
import com.example.rest.member.application.port.out.UpdateMemberPort;
import com.example.rest.member.application.port.out.RegisterMemberPort;
import com.example.rest.member.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class WriteMemberService implements WriteMemberUseCase {

    private final RegisterMemberPort registerMemberPort;
    private final UpdateMemberPort updateMemberPort;

    private final ReadMemberService readService;

    @Override
    public MemberDTO registerMember(@Valid RegisterMemberCommand registerMemCmd) {

        MemberJpaEntity memberJpaEntity = registerMemberPort.registerMember(
                Member.builder()
                        .memberId(registerMemCmd.getMemberId())
                        .password(registerMemCmd.getPassword())
                        .name(registerMemCmd.getName())
                        .email(registerMemCmd.getEmail())
                        .build()
        );
        return readService.mapToDTO(memberJpaEntity);
    }

    @Override
    public MemberDTO updateMember(UpdateMemberCommand updateMemCmd) {
        MemberJpaEntity memberJpaEntity = updateMemberPort.updateMember(
                Member.builder()
                        .memberId(updateMemCmd.getMemberId())
                        .password(updateMemCmd.getPassword())
                        .name(updateMemCmd.getName())
                        .email(updateMemCmd.getEmail())
                        .build()
        );
        return readService.mapToDTO(memberJpaEntity);
    }
}