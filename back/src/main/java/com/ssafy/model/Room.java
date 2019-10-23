package com.ssafy.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
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
public @Data
class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;

    @Type(type = "jsonb")
    @Column(name = "SUMMARY", columnDefinition = "jsonb")
    private String summary;

    @Column(name = "ROOM_FLAG", nullable = false)
    private Boolean roomFlag;

    @Column(name = "COMPLETE_FLAG", nullable = false)
    private Boolean completeFlag;

    @Column(name = "USER_COUNT", nullable = false)
    private Short userCount;

    @OneToMany(mappedBy = "room")
    private List<Sprint> sprints = new ArrayList<Sprint>();

    public void addSprint(Sprint sprint) {
        this.sprints.add(sprint);
        if (sprint.getRoom() != this) {
            sprint.setRoom(this);
        }
    }
}
