package com.ensas.userloginregistration.web;

import com.ensas.userloginregistration.appuser.AppUser;
import com.ensas.userloginregistration.appuser.AppUserRole;
import com.ensas.userloginregistration.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    public String register(RegistrationRequest request){
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid){
            throw new IllegalStateException("The email is invalid");
        }
        return appUserService.signup(
          new AppUser(request.getUserName(),request.getPassword(),request.getEmail(), AppUserRole.USER)
        );
    }
}
