package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROOM_LISTS")
public @Data
class RoomList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_LIST_ID", nullable = false)
    private Long id;

    @Column(name = "ROOM_ID", nullable = false)
    private Long roomId;

    @Column(name = "USER_ID", nullable = false)
    private  Long userId;
}
