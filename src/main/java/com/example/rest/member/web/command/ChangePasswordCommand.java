package com.example.rest.member.web.command;

import lombok.Data;

@Data
public class ChangePasswordCommand {

    private String memberId;
    private String oldPassword;
    private String newPassword;
}
