package com.ssafy.controller;

import com.ssafy.exception.ResourceNotFoundException;
import com.ssafy.model.ModelRoom;
import com.ssafy.model.ModelRoomInput;
import com.ssafy.model.User;
import com.ssafy.repository.ModelRoomRepository;
import com.ssafy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ModelRoomCtroller {

    private final ModelRoomRepository modelRoomRepository;

    private final UserRepository userRepository;

    @Autowired
    public ModelRoomCtroller(ModelRoomRepository modelRoomRepository, UserRepository userRepository) {
        this.modelRoomRepository = modelRoomRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/modelrooms")
    public List<ModelRoom> getAllModelRoom(){
        return modelRoomRepository.findAll();
    }

    @GetMapping("/modelrooms/{id}")
    public ModelRoom getModelRoomById(@PathVariable(value = "id") Long modelId) throws ResourceNotFoundException{
        ModelRoom modelRoom = modelRoomRepository.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("ModelRoom not found for this id :: " + modelId));
        return modelRoom;
    }

    @GetMapping("/modelrooms/user/{id}")
    public List<ModelRoom> getModelRoomByUserId(@PathVariable(value = "id") Long userId){
        List<ModelRoom> modelRoom = modelRoomRepository.findByUserId(userId);
        return modelRoom;
    }

    @PostMapping("/modelrooms")
    public boolean createModelRoom(@Valid @RequestBody ModelRoomInput modelRoomInput) {
        User user = userRepository.findByEmail(modelRoomInput.getEmail());
        ModelRoom modelRoom = new ModelRoom();
        modelRoom.setRoomData(modelRoomInput.getRoomData());
        modelRoom.setUserId(user.getId());
        modelRoom.setImg(modelRoomInput.getImg());
        modelRoom.setUserCount(modelRoomInput.getUserCount());
        ModelRoom newModelRoom = modelRoomRepository.save(modelRoom);
        if(newModelRoom == null) return false;
        return true;
    }

    @PutMapping("/modelrooms/plusUserCount/{id}")
    public ResponseEntity<ModelRoom> updateModelRoomUserCount(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        ModelRoom modelRoom = modelRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ModelRoom not fount for this id :: " + id));
        modelRoom.setUserCount(modelRoom.getUserCount()+1);
        final ModelRoom updated = modelRoomRepository.save(modelRoom);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/modelrooms/{id}")
    public Map<String, Boolean> deleteModelRoom(@PathVariable(value = "id") Long modelId)
            throws ResourceNotFoundException {
        ModelRoom modelRoom = modelRoomRepository.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("ModelRoom not found for this id :: " + modelId));

        modelRoomRepository.delete(modelRoom);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
