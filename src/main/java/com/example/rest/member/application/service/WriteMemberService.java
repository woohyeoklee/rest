package com.example.rest.member.application.service;

import com.example.rest.member.domain.MemberDTO;
import com.example.rest.member.adapter.out.MemberJpaEntity;


import com.example.rest.member.application.port.in.DeleteMemberCommand;
import com.example.rest.member.application.port.in.RegisterMemberCommand;
import com.example.rest.member.application.port.in.WriteMemberUseCase;
import com.example.rest.member.application.port.out.DeleteMemberPort;
import com.example.rest.member.application.port.out.RegisterMemberPort;
import com.example.rest.member.domain.Member;
import com.example.rest.utils.SHA256Util;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class WriteMemberService implements WriteMemberUseCase {

    private final RegisterMemberPort registerMemberPort;
    private final DeleteMemberPort deleteMemberPort;

    private final ReadMemberService readService;

    @Override
    public MemberDTO registerMember(@Valid RegisterMemberCommand registerCommand) {

        var hashedPassword = SHA256Util.encryptSHA256(registerCommand.getPassword());
        MemberJpaEntity memberJpaEntity = registerMemberPort.registerMember(
                Member.builder()
                        .memberId(registerCommand.getMemberId())
                        .password(hashedPassword)
                        .name(registerCommand.getName())
                        .email(registerCommand.getEmail())
                        .build()
        );
        return readService.mapToDTO(memberJpaEntity);
    }

    @Override
    public void deleteMember(DeleteMemberCommand command) {
        deleteMemberPort.deleteMember(
                command.getMemberId(),
                command.getPassword());

    }
}