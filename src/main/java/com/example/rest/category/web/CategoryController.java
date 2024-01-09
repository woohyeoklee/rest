package com.example.rest.category.web;

import com.example.rest.member.application.service.ReadMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@Log4j2
@RequiredArgsConstructor
public class CategoryController {

    private final ReadMemberService readMemberService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
//    @LoginCheck() TODO: 일반 유저가 등록하지 못하도록 어드민 유저만
    public void register(String accountId, @RequestBody CategoryDTO categoryDTO) {

    }
}
