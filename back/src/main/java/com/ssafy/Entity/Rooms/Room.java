package com.ssafy.Entity.Rooms;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    private Boolean roomFlag;
    private Boolean completeFlag;
    private Integer userCount;
}
