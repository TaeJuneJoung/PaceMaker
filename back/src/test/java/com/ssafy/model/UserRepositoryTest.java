package com.ssafy.model;

import com.ssafy.exception.ResourceNotFoundException;
import com.ssafy.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void UserDelete() throws ResourceNotFoundException {
        Long userId = 3L;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        response.forEach((key,value) -> {
            System.out.println(key+"::"+value);
        });
    }


    @Test
    public void UserSaveAndFindTest() throws NoSuchAlgorithmException {

        User yunki = new User();
        yunki.setEmail("byg23424@naver.com");
        yunki.setNickname("닉네임72");
        yunki.setPassword(sha256("1234"));
        yunki.setActivateFlag(true);
        yunki.setAuthenticationFlag(false);
        yunki.setRegisterDate(new Date());
        yunki.setPoint(1000);
        yunki.setAlarmFlag(false);

        User newUser = userRepository.save(yunki);

        assertThat(newUser).isNotNull();

        User foundUser = userRepository.findByEmail(newUser.getEmail());

        if (foundUser != null) {
            assertThat(foundUser.getNickname())
                    .isEqualTo(newUser.getNickname());
        } else {
            System.out.println("널!");
        }
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
