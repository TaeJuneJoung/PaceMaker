package com.ssafy.repository;

import com.ssafy.model.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    UserAchievement findByUserId(Long userId);
}
