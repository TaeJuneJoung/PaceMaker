package com.ssafy.Entity.Rooms;

import com.ssafy.Entity.Sprints.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
