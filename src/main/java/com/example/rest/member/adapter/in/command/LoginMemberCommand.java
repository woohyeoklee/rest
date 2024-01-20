package com.example.rest.member.adapter.in.command;

import lombok.Data;

@Data
public class LoginMemberCommand {

    private String memberId;
    private String password;

}
