package com.example.rest.member.application.port.out;

import com.example.rest.member.adapter.out.MemberJpaEntity;
import org.springframework.stereotype.Component;

@Component
public interface DeleteMemberPort {

    void deleteMember(String memberId, String password);
}
