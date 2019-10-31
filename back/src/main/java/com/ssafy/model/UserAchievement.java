package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USER_ACHIEVEMENTS")
public @Data
class UserAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ACHIEVEMENTS_ID", nullable = false)
    private Long id;

    @Column(name = "coin", nullable = false)
    private Long coin;

    @Column(name = "model_room", nullable = false)
    private Long modelRoom;

    @Column(name = "room", nullable = false)
    private Long room;

    @Column(name = "comment", nullable = false)
    private Long comment;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
