package com.ssafy.controller;

import com.ssafy.exception.ResourceNotFoundException;
import com.ssafy.model.Room;
import com.ssafy.model.RoomSprintInput;
import com.ssafy.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RoomCtroller {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms/user/{id}")
    public List<Room> getAllRoomsByUserId(@PathVariable(value = "id") Long userId) {
        return roomRepository.findByUserId(userId);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/user/{uid}/modelroom/{rid}")
    public Long getRoomByUserIdAndModelRoomId(@PathVariable(value ="uid") Long userId, @PathVariable(value ="rid") Long roomId) {
        return roomRepository.countByUserIdAndModelId(userId, roomId);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId) throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createEmployee(@Valid @RequestBody Room employee) {
        return roomRepository.save(employee);
    }

    @PutMapping("/rooms/sprints/{id}")
    public ResponseEntity<Room> updateSprints(@PathVariable(value = "id") Long roomId,
                                           @Valid @RequestBody RoomSprintInput roomDetails) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));

        room.setSprints(roomDetails.getSprints());
        final Room updatedEmployee = roomRepository.save(room);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long roomId,
                                                   @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));

        room.setSprints(roomDetails.getSprints());
        room.setCompleteFlag(roomDetails.getCompleteFlag());
        room.setRoomFlag(roomDetails.getRoomFlag());
        final Room updatedEmployee = roomRepository.save(room);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));

        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
