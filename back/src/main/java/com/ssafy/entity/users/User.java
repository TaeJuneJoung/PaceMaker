package com.ssafy.entity.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "USERS")
public @Data class User {

    @Id @GeneratedValue
    private Long userId;

    private String userEmail;
    private String userNickname;
    private String userPassword;
    private Boolean userAuthenticationFlag;
    private Boolean userActivateFlag;
    private Date userRegisterDate;
    private Date userDeativateDate;
    private Integer userPoint;
}
