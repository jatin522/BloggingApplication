package com.example.BloggingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BloggingApplication.entity.Comment;
import com.example.BloggingApplication.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{discussionId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long discussionId, @RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.addComment(discussionId, comment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        return new ResponseEntity<>(commentService.updateComment(id, commentDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<List<Comment>> listComments(@PathVariable Long discussionId) {
        return new ResponseEntity<>(commentService.listComments(discussionId), HttpStatus.OK);
    }

    @PostMapping("/{commentId}/likes")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId, @RequestBody Long userId) {
        commentService.likeComment(commentId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{commentId}/replies")
    public ResponseEntity<Void> replyToComment(@PathVariable Long commentId, @RequestBody Comment reply) {
        commentService.replyToComment(commentId, reply);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
