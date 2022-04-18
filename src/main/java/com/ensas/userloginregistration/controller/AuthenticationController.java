package com.ensas.userloginregistration.controller;

import com.ensas.userloginregistration.dto.UserDto;
import com.ensas.userloginregistration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/authenticate")
@AllArgsConstructor
public class AuthenticationController {

    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(registrationService.register(userDto), HttpStatus.CREATED);
    }

    @GetMapping(path ="confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@RequestParam("token") String token){
        registrationService.confirmToken(token);
    }

}
