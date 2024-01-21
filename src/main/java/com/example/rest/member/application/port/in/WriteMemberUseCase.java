package com.example.rest.member.application.port.in;

import com.example.rest.member.domain.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public interface WriteMemberUseCase {

    MemberDTO registerMember(RegisterMemberCommand command);

    void deleteMember(DeleteMemberCommand command);

}
