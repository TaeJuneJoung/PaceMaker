package com.ssafy.Entity.Sprints;

import com.ssafy.Entity.Days.Day;
import com.ssafy.Entity.Rooms.Room;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "SPRINTS")
public class Sprint {

    @Id @GeneratedValue
    private Long sprintID;

    @OneToMany(mappedBy = "sprint")
    private Collection<Day> days;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private Date sprintStartDate;
    private Date sprintEndDate;

    private String sprintGoal;
    private Boolean sprintCompleteFlag;



}
