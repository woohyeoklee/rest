package com.example.rest.member.web.command;

import lombok.Data;

@Data
public class LoginMemberCommand {

    private String memberId;
    private String password;

}
