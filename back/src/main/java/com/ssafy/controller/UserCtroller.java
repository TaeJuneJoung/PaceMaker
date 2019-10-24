package com.ssafy.controller;

import com.ssafy.exception.ResourceNotFoundException;
import com.ssafy.model.User;
import com.ssafy.model.UserEmailandPass;
import com.ssafy.repository.UserRepository;
import com.ssafy.utility.HashEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserCtroller {

    private final UserRepository userRepository;

    private final HashEncoder hashEncoder;

    @Autowired
    public UserCtroller(HashEncoder hashEncoder, UserRepository userRepository) {
        this.hashEncoder = hashEncoder;
        this.userRepository = userRepository;
    }

    /**
     *
     * @return 모든 User info
     */
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{email:.+}")
    public User getEmailUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(userEmail);
        return user;
    }

    /**
     *
     * @param userId
     * @return userId와 일치하는 User
     * @throws ResourceNotFoundException
     */
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return user;
    }

    @PostMapping("/users/login")
    public User loginCheck(@Valid @RequestBody UserEmailandPass userDetails) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(userDetails.getEmail());
        if(user.getEmail().equals(user.getEmail()) && hashEncoder.sha256(userDetails.getPassword()).equals(user.getPassword()))
            return user;
        return null;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User userDetails) throws NoSuchAlgorithmException {
        userDetails.setPassword(hashEncoder.sha256(userDetails.getPassword()));
        return userRepository.save(userDetails);
    }

    @PutMapping("/users") // 사진, 닉네임, 알람설정
    public ResponseEntity<User> updateUser(@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        Long userId = userDetails.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setNickname(userDetails.getNickname());
        user.setImg(userDetails.getImg());
        user.setAlarmFlag(userDetails.getAlarmFlag());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/users/pass")
    public ResponseEntity<User> updatePass(@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        Long userId = userDetails.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setPassword(userDetails.getPassword());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
