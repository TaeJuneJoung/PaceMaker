package com.ssafy.controller;

import com.ssafy.model.AuthMail;
import com.ssafy.model.User;
import com.ssafy.repository.AuthMailRepository;
import com.ssafy.repository.UserRepository;
import com.ssafy.utility.HashEncoder;
import com.ssafy.utility.AuthMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@RestController
@RequestMapping("/api/v1")
public class MailCtroller {

    private final AuthMailSender mailSender;

    private final HashEncoder hashEncoder;

    private final AuthMailRepository authMailRep;

    private  final UserRepository userRepository;

    @Autowired
    public MailCtroller(AuthMailSender mailSender, HashEncoder hashEncoder, AuthMailRepository authMailRep,UserRepository userRepository) {
        this.mailSender = mailSender;
        this.hashEncoder = hashEncoder;
        this.authMailRep = authMailRep;
        this.userRepository = userRepository;
    }

    @GetMapping("/send/{userEmail:.+}")
    public void sendMail(@PathVariable @Valid String userEmail) {

        try {
            // 1. 해싱 - 현재시각과 이메일을 사용하여 SHA1 다이제스트를 만든다. (sha1 - 8초, sha256 - 12초 )
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.KOREA);
            String dateToString = dateFormat.format(new Timestamp(System.currentTimeMillis()));
            String hash = hashEncoder.sha1(dateToString + userEmail);

            // 2. To. 설정(회원의 이메일 주소)
            mailSender.setTo(userEmail);

            // 3. 인증 값을 DB에 저장 OR 갱신
            AuthMail auth = authMailRep.findByEmail(userEmail);
            if (auth == null) {
                auth = new AuthMail();
                auth.setEmail(userEmail);
            }
            auth.setAuth(hash);
            auth.setTime(new Date());
            authMailRep.save(auth);

            // 4. 메일 제목과 본문
            mailSender.setSubject("[PaceMaker] 회원가입 인증 메일");
            mailSender.setText(new StringBuffer().append("<h1>회원가입 인증메일입니다.</h1>")
                    .append("<p>밑의 링크를 클릭하면 메일이 인증 됩니다.</p>").append("<a href='http://13.124.241.124:8080/api/v1/auth/?email=" + userEmail)
                    .append("&hash=" + hash + "' target='_blank'>메일 인증 링크</a>")
                    .toString()
            );

            // 5. 전송
            mailSender.send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/auth/")
    public String authMail(@RequestParam("email") String userEmail, @RequestParam("hash") String userHash) {
        AuthMail auth = authMailRep.findByEmail(userEmail);
        String url = "http://13.124.241.124:3000/";
        if (auth.getAuth().equals(userHash)) {
            User user = userRepository.findByEmail(userEmail);
            user.setAuthenticationFlag(true);
            userRepository.save(user);
            return "<script>location.href = '"+url+"'</script>";
        }
        return "<script>alert('인증에 실패하였습니다.');</script>";
    }
}