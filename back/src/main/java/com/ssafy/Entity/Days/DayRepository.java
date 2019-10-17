package com.ssafy.Entity.Days;

import com.ssafy.Entity.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findByDay(int sprintId);
}
