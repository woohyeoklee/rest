package com.example.rest.member.adapter.in;

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
