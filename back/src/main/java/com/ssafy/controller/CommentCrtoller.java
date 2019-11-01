package com.ssafy.controller;

import com.ssafy.model.Comment;
import com.ssafy.model.CommetInput;
import com.ssafy.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentCrtoller {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentCrtoller(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments/model/{id}")
    public List<Comment> getCommentBy(@PathVariable(value = "id") Long modelRoomId){
        List<Comment> modelRoom = commentRepository.findByModelRoomId(modelRoomId);
        return modelRoom;
    }

    @PostMapping("/comments")
    public Comment createComments(@Valid @RequestBody CommetInput commentInput) {
        Comment comment = new Comment();
        comment.setNickname(commentInput.getNickname());
        comment.setModelRoomId(commentInput.getModelRoomId());
        comment.setContext(commentInput.getContext());
        comment.setDate(new Date());
        return commentRepository.save(comment);
    }
}
