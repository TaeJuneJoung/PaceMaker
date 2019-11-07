package com.ssafy.controller;

import com.ssafy.model.Comment;
import com.ssafy.model.CommetInput;
import com.ssafy.model.User;
import com.ssafy.repository.CommentRepository;
import com.ssafy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentCrtoller {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentCrtoller(CommentRepository commentRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments/model/{id}")
    public List<Comment> getCommentBy(@PathVariable(value = "id") Long modelRoomId){
        List<Comment> modelRoom = commentRepository.findByModelRoomId(modelRoomId);
        return modelRoom;
    }

    @PostMapping("/comments")
    public Comment createComments(@Valid @RequestBody CommetInput commentInput) {
        User user = userRepository.findByNickname(commentInput.getNickname());
        Comment comment = new Comment();
        comment.setNickname(commentInput.getNickname());
        comment.setModelRoomId(commentInput.getModelRoomId());
        comment.setUserId(user.getId());
        comment.setContext(commentInput.getContext());
        comment.setDate(new Date());
        return commentRepository.save(comment);
    }
}
