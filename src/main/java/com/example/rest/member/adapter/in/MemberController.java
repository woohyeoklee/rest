package com.example.rest.member.adapter.in;

import com.example.rest.member.adapter.in.request.UpdateMemberRequest;
import com.example.rest.member.application.port.in.command.UpdateMemberCommand;
import com.example.rest.member.application.port.in.command.RegisterMemberCommand;
import com.example.rest.member.application.port.in.usecase.WriteMemberUseCase;
import com.example.rest.member.adapter.in.request.RegisterMemberRequest;
import com.example.rest.member.domain.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 회원정보수정
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MemberDTO> updateMember(@SessionAttribute(name = "memberId", required = false) String memberId, UpdateMemberRequest updateReq) {
        try {
            if (memberId != null) {
                log.info("session member id : {} ", memberId);
                UpdateMemberCommand updateCommand = UpdateMemberCommand.builder()
                        .memberId(memberId)
                        .password(updateReq.getPassword())
                        .name(updateReq.getName())
                        .email(updateReq.getEmail())
                        .build();
                return ResponseEntity.ok(writeMemberUseCase.updateMember(updateCommand));
            } else {
                log.info("session member id is null");
                // 세션에서 memberId가 없는 경우 UNAUTHORIZED 상태 코드 반환
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            log.error("Error updating member information", e);
            // 예외가 발생한 경우 INTERNAL_SERVER_ERROR 상태 코드 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // 회원탈퇴
}

