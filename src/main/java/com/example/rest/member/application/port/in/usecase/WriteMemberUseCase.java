package com.example.rest.member.application.port.in.usecase;

import com.example.rest.member.application.port.in.command.RegisterMemberCommand;
import com.example.rest.member.application.port.in.command.UpdateMemberCommand;
import com.example.rest.member.domain.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public interface WriteMemberUseCase {

    MemberDTO registerMember(RegisterMemberCommand command);

    //삭제 시 업데이트로 데이터만 변경
    MemberDTO updateMember(UpdateMemberCommand command);

}
