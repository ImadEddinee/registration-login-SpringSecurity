package com.ensas.userloginregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String token;
}
