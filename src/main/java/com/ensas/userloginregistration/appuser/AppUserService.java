package com.ensas.userloginregistration.appuser;

import com.ensas.userloginregistration.web.token.ConfirmationToken;
import com.ensas.userloginregistration.web.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format("User with email %s not found",email)));
    }

    public String signup(AppUser user){
        boolean present = userRepository.findByEmail(user.getEmail()).isPresent();
        if (present){
            throw new IllegalStateException("The email already exists");
        }
        String encoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
          token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public void enableAppUser(String email){
        AppUser appUser = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User email not found")
        );
        appUser.setEnabled(true);
        userRepository.save(appUser);
    }
}
