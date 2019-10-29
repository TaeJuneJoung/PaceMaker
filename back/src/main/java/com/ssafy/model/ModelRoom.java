package com.ssafy.model;

import javax.persistence.*;

@Entity
@Table(name = "MODEL_ROOM")
public class ModelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ROOM_DATA", nullable = false)
    private String roomData;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    public void setUser(User user) {
//        this.user = user;
//
//        if (!user.getModelRooms().contains(this)) {
//            user.getModelRooms().add(this);
//        }
//    }
}
