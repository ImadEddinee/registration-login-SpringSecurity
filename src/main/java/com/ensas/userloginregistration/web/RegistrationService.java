package com.ensas.userloginregistration.web;

import com.ensas.userloginregistration.appuser.AppUser;
import com.ensas.userloginregistration.appuser.AppUserRole;
import com.ensas.userloginregistration.appuser.AppUserService;
import com.ensas.userloginregistration.web.token.ConfirmationToken;
import com.ensas.userloginregistration.web.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request){
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid){
            throw new IllegalStateException("The email is invalid");
        }
        return appUserService.signup(
          new AppUser(request.getUserName(),request.getPassword(),request.getEmail(), AppUserRole.USER)
        );
    }
    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken =confirmationTokenService
                .getToken(token)
                .orElseThrow(()->new IllegalStateException("Token not found"));
        if (confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
