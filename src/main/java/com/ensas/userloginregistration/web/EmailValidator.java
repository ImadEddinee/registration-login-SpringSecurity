package com.ensas.userloginregistration.web;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // Logique to validate the email
        return true;
    }
}
