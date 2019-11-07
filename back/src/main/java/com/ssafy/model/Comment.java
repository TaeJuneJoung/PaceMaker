package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public @Data
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "context", nullable = false)
    private String context;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "model_room_id", nullable = false)
    private Long modelRoomId;
}
