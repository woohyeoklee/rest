package com.example.rest.member.adapter.in;

import com.example.rest.member.adapter.in.command.DeleteMemberRequest;
import com.example.rest.member.application.port.in.DeleteMemberCommand;
import com.example.rest.member.application.port.in.RegisterMemberCommand;
import com.example.rest.member.application.port.in.WriteMemberUseCase;
import com.example.rest.member.adapter.in.command.RegisterMemberRequest;
import com.example.rest.member.domain.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/members")
public class MemberController {
    private final WriteMemberUseCase writeMemberUseCase;


    // 회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO register(@RequestBody RegisterMemberRequest registerReq) {
        RegisterMemberCommand registerCommand = RegisterMemberCommand.builder()
                .memberId(registerReq.getMemberId())
                .password(registerReq.getPassword())
                .name(registerReq.getName())
                .email(registerReq.getEmail())
                .build();

        return writeMemberUseCase.registerMember(registerCommand);
    }

    // 회원탈퇴
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@SessionAttribute(name = "memberId", required = false) DeleteMemberRequest deleteMemReq) {
        DeleteMemberCommand deleteMemberCommand = DeleteMemberCommand.builder()
                .memberId(deleteMemReq.getMemberId())
                .password(deleteMemReq.getPassword())
                .build();
        writeMemberUseCase.deleteMember(deleteMemberCommand);
    }
}

