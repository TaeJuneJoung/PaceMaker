package com.ssafy.controller;

import com.sun.mail.util.logging.MailHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MailCtrollerTest {

    @Test
    public void TestSendEmail() {
        String msg = "";
        String resultCode = "";
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername("ssafydocker@gmail.com");
            mailSender.setPassword("ssafy@1234");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            MailCtroller mail = new MailCtroller(mailSender);

            String memberMail = "byg9543@gmail.com";
            mail.setFrom("ssafydocker@gmail.com", "docker");
            mail.setTo(memberMail);
            int authKey = 1111;
            mail.setSubject("페이지 회원가입 인증 메일");
            mail.setText(new StringBuffer().append("<h1>회원가입 인증메일입니다.</h1>")
                    .append("<p>밑의 링크를 클릭하면 메일이 인증 됩니다.</p>").append("<a href='http://localhost/member/auth?email=" + memberMail)
                    .append("&authKey="+authKey+"'target='_blank'>메일 인증 링크</a>")
                    .toString()
            );
            mail.send();
            msg = "회원가입 성공..작성하신 이메일로 인증메일을 전송하였습니다.";
            resultCode = "S-1";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "회원가입 실패";
            resultCode = "F-1";
        } finally {
            System.out.println(resultCode + "::" + msg);
        }
    }
}