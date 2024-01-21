package com.example.rest.member.adapter.out;

import com.example.rest.member.application.port.out.UpdateMemberPort;
import com.example.rest.member.application.port.out.FindMemberPort;
import com.example.rest.member.application.port.out.RegisterMemberPort;
import com.example.rest.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberPersistenceAdapter implements RegisterMemberPort, FindMemberPort, UpdateMemberPort {

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
    public MemberJpaEntity updateMember(Member member) {
        // 멤버 아이디로 멤버를 찾아서 빈값으로 세팅하고 저장하면 된다.
        if (member == null || member.getMemberId() == null) {
            throw new IllegalArgumentException("잘못된 멤버 정보입니다.");
        }

        var memberJpaEntity = memberJpaRepository.findByMemberId(member.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        memberJpaEntity.setMemberId(member.getMemberId());
        memberJpaEntity.setPassword(member.getPassword());
        memberJpaEntity.setName(member.getName());
        memberJpaEntity.setEmail(member.getEmail());
        return memberJpaRepository.save(memberJpaEntity);
    }
}
