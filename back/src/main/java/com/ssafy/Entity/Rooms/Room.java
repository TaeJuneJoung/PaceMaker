package com.ssafy.Entity.Rooms;

import com.ssafy.Entity.Sprints.Sprint;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Collection;

@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)

@Entity
public @Data class Room {

    @Id @GeneratedValue
    private Long roomId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String summary;

    @OneToMany(mappedBy = "room")
    private Collection<Sprint> sprints;

    private Boolean roomFlag;
    private Boolean completeFlag;
    private Integer userCount;
}
