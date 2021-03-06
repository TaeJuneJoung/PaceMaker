package com.ssafy.Entity.Days;

import com.ssafy.Entity.Sprints.Sprint;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DAYS")
public class Day {
    @Id @GeneratedValue
    private Long dayId;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    private String daySubject;
    private Boolean dayCompleteFlag;
    private Date dayDate;

}
