package com.ensas.userloginregistration.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RegistrationRequest {
    private String userName;
    private String email;
    private String password;
}
