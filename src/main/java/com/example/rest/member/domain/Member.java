package com.example.rest.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private String memberId;
    private String password;
    private String name;
    private String email;

    public void changePassword(String password) {
        this.password = password;
    }


    @Builder
    public Member(String memberId, String password, String name, String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
