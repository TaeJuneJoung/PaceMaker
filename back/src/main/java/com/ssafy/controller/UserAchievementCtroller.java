package com.ssafy.controller;

import com.ssafy.model.UserAchievement;
import com.ssafy.repository.UserAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserAchievementCtroller {

    private final UserAchievementRepository achievementRepository;

    @Autowired
    public UserAchievementCtroller(UserAchievementRepository userAchievementRepository){
        this.achievementRepository = userAchievementRepository;
    }

    @GetMapping("/usersAchieve/{id}")
    public UserAchievement getUserAchieve(@PathVariable(value = "id") Long userId) {
        UserAchievement userAchievement = achievementRepository.findByUserId(userId);
        return userAchievement;
    }

    @PutMapping("/usersAchieve")
    public ResponseEntity<UserAchievement> updateUserAchieve(@Valid @RequestBody UserAchievement userDetails) {
        final UserAchievement updatedUser = achievementRepository.save(userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/usersAchieve")
    public UserAchievement createUserAchieve(@Valid @RequestBody UserAchievement userDetails) {
        userDetails.setCoin(0L);
        userDetails.setComment(0L);
        userDetails.setModelRoom(0L);
        userDetails.setRoom(0L);
        return achievementRepository.save(userDetails);
    }
}
