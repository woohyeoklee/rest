package com.example.rest.member.application.port.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterMemberCommand {

    private String memberId;

    private String password;

    private String name;

    private String email;

    public RegisterMemberCommand(String memberId, String password, String name, String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
