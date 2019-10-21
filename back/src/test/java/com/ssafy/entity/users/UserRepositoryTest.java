package com.ssafy.entity.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void connectionTest() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getURL());
        }
    }

    @Test
    public void UserSaveAndFindTest() {

        User yunki = new User();
        yunki.setUserEmail("byg9543@naver.com");
        yunki.setUserNickname("yunki");
        yunki.setUserPassword("1234");
        yunki.setUserActivateFlag(true);
        yunki.setUserAuthenticationFlag(false);
        yunki.setUserRegisterDate(new Date());
        yunki.setUserPoint(1000);

        User newUser = userRepository.save(yunki);

        assertThat(newUser).isNotNull();

        Optional<User> foundUser = userRepository.findByUserNickname(newUser.getUserNickname());

        if (foundUser.isPresent()) {
            assertThat(foundUser.get().getUserNickname())
                    .isEqualTo(newUser.getUserNickname());
        } else {
            System.out.println("널!");
        }
    }
}
