package com.ensas.userloginregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AuthenticationRequestDto {
    private String userName;
    private String email;
    private String password;
}
