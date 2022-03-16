package com.ensas.userloginregistration.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    @Override
    public void sendEmail(String to, String email) {

    }
}
