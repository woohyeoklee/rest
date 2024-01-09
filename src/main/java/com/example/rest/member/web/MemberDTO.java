package com.example.rest.member.web;

import com.example.rest.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String memberId;
    private String name;
    private String email;
    private boolean isAdmin;

}
