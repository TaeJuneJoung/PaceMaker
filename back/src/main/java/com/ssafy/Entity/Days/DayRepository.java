package com.ssafy.Entity.Days;

<<<<<<< HEAD
import com.ssafy.Entity.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findByDay(int sprintId);
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {

>>>>>>> c3da53a3ab855885a5ff8ca13f47de4865e405d0
}
