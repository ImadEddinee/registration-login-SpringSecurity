package com.ensas.userloginregistration.email;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(String to, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
        try {
            helper.setText(body, true);
            helper.setTo(to);
            helper.setSubject("Confirm Your Email");
            helper.setFrom("imadhajali66@gmail.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
