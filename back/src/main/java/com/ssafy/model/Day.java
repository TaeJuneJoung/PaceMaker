package com.ssafy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "DAYS")
public @Data
class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAY_ID", nullable = false)
    private Long id;

    @Column(name = "DAY_SUBJECT ", nullable = false)
    private String subject;

    @Column(name = "DAY_COMPLETE_FLAG ", nullable = false)
    private Boolean completeFlag;

    @Column(name = "DAY_DATE ", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "SPRINT_ID")
    private Sprint sprint;
}
