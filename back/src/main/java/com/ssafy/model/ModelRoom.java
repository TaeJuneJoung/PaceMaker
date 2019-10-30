package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MODEL_ROOMS")
public @Data
class ModelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODEL_ROOM_ID", nullable = false)
    private Long id;

    @Column(name = "ROOM_DATA", nullable = false)
    private String roomData;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "IMG_URL")
    private String img;

}
