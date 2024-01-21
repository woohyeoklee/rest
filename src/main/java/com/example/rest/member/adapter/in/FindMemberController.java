package com.example.rest.member.adapter.in;

import com.example.rest.member.application.port.in.command.FindMemberCommand;
import com.example.rest.member.application.port.in.usecase.ReadMemberUseCase;
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
public class FindMemberController {

    private final ReadMemberUseCase readMemberUseCase;

    // 회원조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable(name = "memberId") String memberId) {
        try {
            FindMemberCommand findMemberCommand = FindMemberCommand.builder()
                    .memberId(memberId)
                    .build();
            MemberDTO memberDTO = readMemberUseCase.findByMemberId(findMemberCommand.getMemberId());
            return ResponseEntity.ok(memberDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

