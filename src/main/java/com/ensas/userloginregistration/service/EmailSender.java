package com.ensas.userloginregistration.service;

public interface EmailSender {

    void sendEmail(String to, String body);
}
