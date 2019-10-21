package com.ssafy.entity.sprints;

import com.ssafy.entity.days.Day;
import com.ssafy.entity.rooms.Room;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SPRINTS")
public @Data class Sprint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPRINT_ID", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "sprint")
    private List<Day> days;

    // Lazy fetch 하지 않은 컬럼들만 미리 가져오고, 요청할 때 쿼리를 날려서 맵핑함.
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
