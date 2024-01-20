package com.example.rest.member.adapter.in.command;

import lombok.Data;

@Data
public class DeleteMemberCommand {

    private String memberId;
    private String password;
}
