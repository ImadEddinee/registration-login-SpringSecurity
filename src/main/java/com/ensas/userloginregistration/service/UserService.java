package com.ensas.userloginregistration.service;

import com.ensas.userloginregistration.entity.User;
import com.ensas.userloginregistration.exceptions.ApiRequestException;
import com.ensas.userloginregistration.repository.UserRepository;
import com.ensas.userloginregistration.entity.Token;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format("User with email %s not found",email)));
    }

    public String signup(User user){

        String encoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        Token confirmationToken = new Token();
        confirmationToken.setToken(token);
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public void enableAppUser(String email){
        User appUser = userRepository.findByEmail(email).orElseThrow(
                () -> new ApiRequestException("User email not found")
        );
        appUser.setEnabled(true);
        userRepository.save(appUser);
    }
}
