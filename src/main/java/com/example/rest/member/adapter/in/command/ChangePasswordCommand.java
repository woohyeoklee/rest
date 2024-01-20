package com.example.rest.member.adapter.in.command;

import lombok.Data;

@Data
public class ChangePasswordCommand {

    private String memberId;
    private String oldPassword;
    private String newPassword;
}
