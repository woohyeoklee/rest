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

    //비밀번호 변경 및 검증 메서드
    public void changePassword(String newPassword) {
        validatePasswordChange(newPassword);
        this.password = newPassword;
    }
    public void validatePasswordChange(String newPassword) {
        Assert.hasText(newPassword, "새로운 비밀번호는 필수 입력 값입니다.");

        if (newPassword.equals(this.password)) {
            throw new IllegalArgumentException("새로운 비밀번호는 기존 비밀번호와 달라야 합니다.");
        }
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