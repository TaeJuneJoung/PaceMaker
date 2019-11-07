package com.ssafy.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ROOMS")
public @Data
class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;

    @Column(name = "ROOM_TITLE", nullable = false)
    private String title;

    @Column(name = "STEPS")
    private Long steps;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "MODEL_ROOM_ID")
    private Long modelId;

    @Column(name = "CURRENT_DAY")
    private Long currentDay;

    @Column(name = "ROOM_CREATED_DATE", nullable = false)
    private Date createDate;

    @Column(name = "ROOM_FLAG", nullable = false)
    private Boolean roomFlag;

    @Column(name = "COMPLETE_FLAG", nullable = false)
    private Boolean completeFlag;

    @Column(name = "SPRINTS" , nullable = false)
    private String sprints;
    
}
