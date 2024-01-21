package com.example.rest.member.application.port.in.usecase;

import com.example.rest.member.adapter.in.request.LoginMemberRequest;
import com.example.rest.member.application.port.in.command.LoginMemberCommand;
import com.example.rest.member.domain.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public interface ReadMemberUseCase {

        MemberDTO findByMemberId(String memberId);

        MemberDTO login(LoginMemberCommand loginCommand);
}
