package com.example.rest.member.adapter.in;

import com.example.rest.member.adapter.in.request.LoginMemberRequest;
import com.example.rest.member.application.port.in.command.LoginMemberCommand;
import com.example.rest.member.application.port.in.usecase.ReadMemberUseCase;

import com.example.rest.member.domain.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/members")
public class LoginController {

    private final ReadMemberUseCase readMemberUseCase;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody LoginMemberRequest loginRequest, HttpSession session) {
        try {
            LoginMemberCommand loginCommand = LoginMemberCommand.builder()
                    .memberId(loginRequest.getMemberId())
                    .password(loginRequest.getPassword())
                    .build();
            MemberDTO memberDTO = readMemberUseCase.login(loginCommand);
            session.setAttribute("memberId", memberDTO.getMemberId());
            log.info("session member id : {} ", session.getAttribute("memberId"));
            return ResponseEntity.ok(memberDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberDTO());
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("memberId");
        log.info("User logged out");
        return ResponseEntity.ok().build();
    }
}
