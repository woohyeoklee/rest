package com.example.rest.member.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<MemberJpaEntity, Long> {
    boolean existsByMemberId(String memberId);
    Optional<MemberJpaEntity> findByMemberId(String memberId);

}