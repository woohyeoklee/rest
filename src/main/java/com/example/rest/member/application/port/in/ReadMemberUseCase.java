package com.example.rest.member.application.port.in;

import com.example.rest.member.domain.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public interface ReadMemberUseCase {

        MemberDTO findByMemberId(String memberId);
}
