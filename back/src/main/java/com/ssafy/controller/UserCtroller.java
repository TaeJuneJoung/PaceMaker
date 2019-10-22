package com.ssafy.controller;

import com.ssafy.model.User;
import com.ssafy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserCtroller {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public User createEmployee(@Valid @RequestBody User user) throws NoSuchAlgorithmException {
        user.setPassword(sha256(user.getPassword()));


        return userRepository.save(user);
    }




    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());

        return bytesToHex(md.digest());
    }


    /**
     * 바이트를 헥스값으로 변환한다
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }


}
