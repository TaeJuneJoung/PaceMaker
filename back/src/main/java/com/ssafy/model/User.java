package com.ssafy.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "USERS")
public @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @Column(name = "USER_NICKNAME", nullable = false, unique = true)
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;

    @Column(name = "USER_PASSWORD", nullable = false)
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @Column(name = "USER_AUTHENTICATION_FLAG", nullable = false)
    private Boolean authenticationFlag;

    @Column(name = "USER_ACTIVATE_FLAG", nullable = false)
    private Boolean activateFlag;

    @Column(name = "USER_REGISTER_DATE", nullable = false)
    private Date registerDate;

    @Column(name = "USER_DEATIVATE_DATE", columnDefinition = "DATE", nullable = true)
    private Date deAtivateDate;

    @Column(name = "USER_POINT", nullable = false)
    private Integer point;

    @Column(name = "USER_ALARM_FLAG", nullable = false)
    private Boolean alarmFlag;

    @Column(name = "USER_IMG")
    private String img;
}
