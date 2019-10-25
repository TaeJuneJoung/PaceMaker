package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACHIEVEMENTS")
public @Data
class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACHIEVEMENT_ID", nullable = false)
    private Long id;

    @Column(name = "ACHIEVEMENT_DATE", nullable = false)
    private Date date;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "ACHIEVEMENT_NUMBER", nullable = false)
    private Integer number;


}
