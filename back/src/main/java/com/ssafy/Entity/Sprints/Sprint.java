package com.ssafy.Entity.Sprints;

import com.ssafy.Entity.Days.Day;
import com.ssafy.Entity.Rooms.Room;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "SPRINTS")
public @Data class Sprint {

    @Id @GeneratedValue 
    @Column(name="SPRINT_ID")
    private Long id;

    @OneToMany(mappedBy = "sprint")
    private Collection<Day> days;

    // Lazy fetch 하지 않은 컬럼들만 미리 가져오고, 요청할 때 쿼리를 날려서 맵핑함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private Date sprintStartDate;
    private Date sprintEndDate;

    private String sprintGoal;
    private Boolean sprintCompleteFlag;



}
