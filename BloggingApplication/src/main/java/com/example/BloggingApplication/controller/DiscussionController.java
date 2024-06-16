package com.example.BloggingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BloggingApplication.entity.Discussion;
import com.example.BloggingApplication.service.DiscussionService;

import java.util.List;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @PostMapping
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion) {
        return new ResponseEntity<>(discussionService.createDiscussion(discussion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discussion> updateDiscussion(@PathVariable Long id, @RequestBody Discussion discussionDetails) {
        return new ResponseEntity<>(discussionService.updateDiscussion(id, discussionDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Discussion>> listDiscussions() {
        return new ResponseEntity<>(discussionService.listDiscussions(), HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Discussion>> getDiscussionsByTag(@RequestParam String tag) {
        return new ResponseEntity<>(discussionService.getDiscussionsByTag(tag), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Discussion>> getDiscussionsByText(@RequestParam String text) {
        return new ResponseEntity<>(discussionService.getDiscussionsByText(text), HttpStatus.OK);
    }

    @PostMapping("/{discussionId}/likes")
    public ResponseEntity<Void> likeDiscussion(@PathVariable Long discussionId, @RequestBody Long userId) {
        discussionService.likeDiscussion(discussionId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{discussionId}/views")
    public ResponseEntity<Void> viewDiscussion(@PathVariable Long discussionId) {
        discussionService.viewDiscussion(discussionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
