package com.example.rest.member.application.port.out;

import com.example.rest.member.adapter.out.MemberJpaEntity;
import com.example.rest.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public interface RegisterMemberPort {

    MemberJpaEntity registerMember(Member member);
}
