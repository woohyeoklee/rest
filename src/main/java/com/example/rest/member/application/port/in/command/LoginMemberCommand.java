package com.example.rest.member.application.port.in.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginMemberCommand {

    private String memberId;
    private String password;

    public LoginMemberCommand(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
