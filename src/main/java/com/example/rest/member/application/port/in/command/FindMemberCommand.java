package com.example.rest.member.application.port.in.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindMemberCommand {

    private String memberId;

    public FindMemberCommand(String memberId) {
        this.memberId = memberId;
    }
}
