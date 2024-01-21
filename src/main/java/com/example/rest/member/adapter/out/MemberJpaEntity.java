package com.example.rest.member.adapter.out;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id", unique = true)
    private String memberId;
    private String password;
    private String name;
    private String email;

    public MemberJpaEntity(String memberId, String password, String name, String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}