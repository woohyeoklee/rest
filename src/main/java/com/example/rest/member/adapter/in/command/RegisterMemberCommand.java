package com.example.rest.member.adapter.in.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterMemberCommand {

    @NotBlank(message = "회원 아이디를 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

}
