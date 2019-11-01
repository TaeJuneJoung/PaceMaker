package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SPRINTS")
public @Data
class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPRINT_ID", nullable = false)
    private Long id;

    @Column(name = "SPRINT_START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "SPRINT_END_DATE", columnDefinition = "DATE", nullable = false)
    private Date endDate;

    @Column(name = "SPRINT_GOAL", nullable = false)
    private String goal;

    @Column(name = "SPRINT_COMPLETE_FLAG", nullable = false)
    private Boolean completeFlag;

    @OneToMany(mappedBy = "sprint")
    private List<Day> days;



}
