package com.example.rest.member.adapter.out;

import com.example.rest.member.application.port.out.DeleteMemberPort;
import com.example.rest.member.application.port.out.FindMemberPort;
import com.example.rest.member.application.port.out.RegisterMemberPort;
import com.example.rest.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberPersistenceAdapter implements RegisterMemberPort, FindMemberPort, DeleteMemberPort {

    private final SpringDataJpaMemberRepository memberJpaRepository;


    @Override
    public MemberJpaEntity registerMember(Member member) {
        if (memberJpaRepository.existsByMemberId(member.getMemberId())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        return memberJpaRepository.save(
                new MemberJpaEntity(
                        member.getMemberId(),
                        member.getPassword(),
                        member.getName(),
                        member.getEmail()
                )
        );
    }

    @Override
    public MemberJpaEntity findByMemberId(String memberId) {
        return memberJpaRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }

    @Override
    public void deleteMember(String memberId, String password) {
        MemberJpaEntity memberJpaEntity = memberJpaRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        if (!memberJpaEntity.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        memberJpaRepository.delete(memberJpaEntity);
    }
}
