package com.ensas.userloginregistration.controller;

import com.ensas.userloginregistration.dto.AuthenticationRequestDto;
import com.ensas.userloginregistration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authenticate")
@AllArgsConstructor
public class AuthenticationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody AuthenticationRequestDto requestDto) {
        return "hi";
    }
    @GetMapping(path ="confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
