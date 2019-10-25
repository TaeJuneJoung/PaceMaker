package com.ssafy.Entity.Rooms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RoomRepository roomRepository;

    @Test
    public void di(){
        Room room = new Room();
        room.setRoomFlag(true);
        room.setCompleteFlag(false);
        room.setSummary("{a:123}");
        room.setUserCount(10);
        Room newRoom = roomRepository.save(room);

        assertThat(newRoom).isNotNull();
    }
}