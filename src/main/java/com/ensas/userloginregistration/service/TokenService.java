package com.ensas.userloginregistration.service;

import com.ensas.userloginregistration.entity.Token;
import com.ensas.userloginregistration.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(Token token){
        confirmationTokenRepository.save(token);
    }

    public Optional<Token> getToken(String token){
            return confirmationTokenRepository.findByToken(token);
    }
    public void setConfirmedAt(String token){
        Token confirmationToken = confirmationTokenRepository.findByToken(token).get();
        confirmationToken.setConfirmedAt(LocalDateTime.now());
    }
}
