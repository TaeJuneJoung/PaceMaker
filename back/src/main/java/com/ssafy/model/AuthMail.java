package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUTH_MAILS")
public @Data
class AuthMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "SEND_DATE", nullable = false)
    private Date time;

    @Column(name = "AUTH_NUM", nullable = false)
    private String auth;

}
