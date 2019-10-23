package com.ssafy.controller;

import com.ssafy.exception.ResourceNotFoundException;
import com.ssafy.model.User;
import com.ssafy.repository.UserRepository;
import com.ssafy.utility.HashEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserCtroller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashEncoder hashEncoder;


    @PostMapping("/users")
    public User createEmployee(@Valid @RequestBody User user) throws NoSuchAlgorithmException {

        user.setPassword(hashEncoder.sha256(user.getPassword()));


        return userRepository.save(user);
    }


    @PutMapping("/users")
    public ResponseEntity<User> updatePass(@Valid @RequestBody User roomDetails) throws ResourceNotFoundException {
        Long userId = roomDetails.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setPassword(roomDetails.getPassword());
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
