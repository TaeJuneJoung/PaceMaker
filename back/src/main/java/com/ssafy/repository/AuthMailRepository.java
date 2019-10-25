package com.ssafy.repository;

import com.ssafy.model.AuthMail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthMailRepository extends JpaRepository<AuthMail, Long> {
    AuthMail findByEmail(String email);
}
