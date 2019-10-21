package com.ssafy.entity.rooms;

import com.ssafy.entity.sprints.Sprint;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jdk.internal.jline.internal.Nullable;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)

@Entity
@Table(name = "ROOMS")
public @Data class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String summary;

    @OneToMany(mappedBy = "room")
    private List<Sprint> sprints = new ArrayList<Sprint>();

    private Boolean roomFlag;
    private Boolean completeFlag;
    private Integer userCount;

    public void addSprint(Sprint sprint){
        this.sprints.add(sprint);
        if(sprint.getRoom() != this){
            sprint.setRoom(this);
        }
    }
}
