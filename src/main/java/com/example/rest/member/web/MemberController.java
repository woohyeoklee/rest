package com.example.rest.member.web;

import com.example.rest.member.application.service.ReadMemberService;
import com.example.rest.member.application.service.WriteMemberService;
import com.example.rest.member.domain.Member;
import com.example.rest.member.web.command.ChangePasswordCommand;
import com.example.rest.member.web.command.LoginMemberCommand;
import com.example.rest.member.web.command.RegisterMemberCommand;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.rest.member.domain.Member.mapToDTO;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/members")
public class MemberController {
    private final WriteMemberService writeService;
    private final ReadMemberService readService;


    // 회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterMemberCommand command) {
        writeService.register(command);

    }

    // 회원조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable(name = "memberId") String memberId) {
        try {
            Member member = readService.findByMemberId(memberId);
            MemberDTO memberDTO = mapToDTO(member);
            return ResponseEntity.ok(memberDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberDTO());
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody LoginMemberCommand loginCommand, HttpSession session) {
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

    //로그인 이후의 동작 : 로그아웃, 비밀번호 변경, 회원탈퇴
    // 로그아웃
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    // 비밀번호 변경
    @PatchMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordCommand command) {
        try {
            writeService.changePassword(command);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
    }

    // 회원탈퇴
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus deleteMember(@SessionAttribute(name = "memberId", required = false) String memberId) {
        if (memberId == null) {
            // 세션에 memberId가 없으면 로그인 페이지로 리다이렉트 또는 로그인을 요구하는 응답을 반환
            return HttpStatus.UNAUTHORIZED;
        }
        // 로그인된 상태이므로 회원탈퇴 로직 수행
        writeService.deleteMember(memberId);
        return HttpStatus.OK;
    }
}

