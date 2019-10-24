package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CHAT_MESSAGES")
public @Data
class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_MESSAGE_ID", nullable = false)
    private Long id;

    @Column(name = "ROOM_ID", nullable = false)
    private Long roomId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "CHAT_MESSAGE_DATE", nullable = false)
    private Date date;

    @Column(name = "CHAT_MESSAGE_CONTENT", nullable = false)
    private String content;

}
