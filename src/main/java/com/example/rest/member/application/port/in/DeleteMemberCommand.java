package com.example.rest.member.application.port.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteMemberCommand {

    private String memberId;
    private String password;

    public DeleteMemberCommand(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
