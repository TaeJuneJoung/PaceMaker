package com.ssafy.utility;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class AuthMailSender {

    private JavaMailSenderImpl mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    //MailCtroller의 생성자
    public AuthMailSender() throws Exception {
        mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("ssafydocker@gmail.com");
        mailSender.setPassword("ssafy@1234");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        message = mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom("ssafydocker@gmail.com", "docker");
    }

    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    public void setText(String text) throws MessagingException {
        messageHelper.setText(text, true);
    }

    public void send() {
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
