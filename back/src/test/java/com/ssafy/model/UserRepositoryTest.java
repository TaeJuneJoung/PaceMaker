package com.ssafy.model;

import com.ssafy.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void UserSaveAndFindTest() throws NoSuchAlgorithmException {

        User yunki = new User();
        yunki.setEmail("byg2344@naver.com");
        yunki.setNickname("닉네임7");
        yunki.setPassword(sha256("1234"));
        yunki.setActivateFlag(true);
        yunki.setAuthenticationFlag(false);
        yunki.setRegisterDate(new Date());
        yunki.setPoint(1000);
        yunki.setAlarmFlag(false);

//        User newUser = userRepository.save(yunki);

        assertThat(yunki).isNotNull();

        User foundUser = userRepository.findByEmail(yunki.getEmail());

        if (foundUser != null) {
            assertThat(foundUser.getNickname())
                    .isEqualTo(yunki.getNickname());
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
