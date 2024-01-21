package com.example.rest.member.adapter.in.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginMemberRequest {

    @NotBlank(message = "회원 아이디를 입력해주세요.")
    private String memberId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

}
