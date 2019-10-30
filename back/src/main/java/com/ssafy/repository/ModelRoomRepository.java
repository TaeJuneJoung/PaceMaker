package com.ssafy.repository;

import com.ssafy.model.ModelRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRoomRepository extends JpaRepository<ModelRoom,Long> {

    List<ModelRoom> findByUserId(Long userId);
}
