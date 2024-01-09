package com.example.rest.member.domain;

import com.example.rest.member.web.MemberDTO;
import com.example.rest.member.web.command.ChangePasswordCommand;
import com.example.rest.utils.SHA256Util;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;


@Builder
@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    public enum Role {
        ADMIN, USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id", unique = true)
    private String memberId;
    private String password;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void changePassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return Member.Role.ADMIN.equals(role);
    }

    public static MemberDTO mapToDTO(Member member) {
        return new MemberDTO(
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.isAdmin()
        );
    }


}