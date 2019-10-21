package com.ssafy.entity.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoomServiceImpl {

    @Autowired
    RoomRepository roomRepository;

    public Room getRoom(Long id) throws EntityNotFoundException{
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }


}
