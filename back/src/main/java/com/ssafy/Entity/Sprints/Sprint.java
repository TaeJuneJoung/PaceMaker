package com.ssafy.Entity.Sprints;

import com.ssafy.Entity.Days.Day;
import com.ssafy.Entity.Rooms.Room;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SPRINTS")
public @Data class Sprint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPRINT_ID")
    private Long id;

    @OneToMany(mappedBy = "sprint")
    private List<Day> days;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private Date sprintStartDate;
    private Date sprintEndDate;

    private String sprintGoal;
    private Boolean sprintCompleteFlag;

    public void setRoom(Room room) {
        this.room = room;

        if(!room.getSprints().contains(this)){
            room.getSprints().add(this);
        }
    }


}
