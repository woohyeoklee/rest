package com.example.rest.member.adapter.in;

import com.example.rest.member.adapter.in.command.LoginMemberRequest;
import com.example.rest.member.application.service.ReadMemberService;
import com.example.rest.member.application.service.WriteMemberService;
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
    private final WriteMemberService writeService;
    private final ReadMemberService readService;


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody LoginMemberRequest loginCommand, HttpSession session) {
        // 로그인 성공시 세션에 memberId를 저장
        try {
            MemberDTO memberDTO = readService.login(loginCommand);
            session.setAttribute("memberId", memberDTO.getMemberId());
            log.info("session member id : {} ", session.getAttribute("memberId"));
            return ResponseEntity.ok(memberDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberDTO());
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
