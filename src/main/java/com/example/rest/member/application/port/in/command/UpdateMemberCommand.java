package com.example.rest.member.application.port.in.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMemberCommand {

    private String memberId;
    private String password;
    private String name;
    private String email;

    public UpdateMemberCommand(String memberId, String password, String name, String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
