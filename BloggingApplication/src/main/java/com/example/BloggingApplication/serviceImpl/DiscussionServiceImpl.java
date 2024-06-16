package com.example.BloggingApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BloggingApplication.entity.Discussion;
import com.example.BloggingApplication.entity.User;
import com.example.BloggingApplication.repository.DiscussionRepository;
import com.example.BloggingApplication.repository.UserRepository;
import com.example.BloggingApplication.service.DiscussionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Discussion createDiscussion(Discussion discussion) {
        discussion.setCreatedOn(LocalDateTime.now());
        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion updateDiscussion(Long id, Discussion discussionDetails) {
        Optional<Discussion> discussionOptional = discussionRepository.findById(id);
        if (!discussionOptional.isPresent()) {
            return null; // Handle discussion not found
        }
        Discussion discussion = discussionOptional.get();
        discussion.setText(discussionDetails.getText());
        discussion.setImage(discussionDetails.getImage());
        discussion.setHashtags(discussionDetails.getHashtags());
        return discussionRepository.save(discussion);
    }

    @Override
    public void deleteDiscussion(Long id) {
        Optional<Discussion> discussionOptional = discussionRepository.findById(id);
        if (!discussionOptional.isPresent()) {
            // Handle discussion not found
            return;
        }
        discussionRepository.delete(discussionOptional.get());
    }

    @Override
    public List<Discussion> listDiscussions() {
        return discussionRepository.findAll();
    }

    @Override
    public List<Discussion> getDiscussionsByTag(String tag) {
        return discussionRepository.findByHashtagsContaining(tag);
    }

    @Override
    public List<Discussion> getDiscussionsByText(String text) {
        return discussionRepository.findByTextContaining(text);
    }

    @Override
    public void likeDiscussion(Long discussionId, Long userId) {
        Optional<Discussion> discussionOptional = discussionRepository.findById(discussionId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (!discussionOptional.isPresent() || !userOptional.isPresent()) {
            // Handle discussion or user not found
            return;
        }
        Discussion discussion = discussionOptional.get();
        User user = userOptional.get();
        discussion.getLikes().add(user);
        discussionRepository.save(discussion);
    }

    @Override
    public void viewDiscussion(Long discussionId) {
        Optional<Discussion> discussionOptional = discussionRepository.findById(discussionId);
        if (!discussionOptional.isPresent()) {
            // Handle discussion not found
            return;
        }
        Discussion discussion = discussionOptional.get();
        discussion.setViewCount(discussion.getViewCount() + 1);
        discussionRepository.save(discussion);
    }
}
