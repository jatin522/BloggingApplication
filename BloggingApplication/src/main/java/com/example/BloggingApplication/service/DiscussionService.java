package com.example.BloggingApplication.service;

import java.util.List;

import com.example.BloggingApplication.entity.Discussion;

public interface DiscussionService {
    Discussion createDiscussion(Discussion discussion);
    Discussion updateDiscussion(Long id, Discussion discussionDetails);
    void deleteDiscussion(Long id);
    List<Discussion> listDiscussions();
    List<Discussion> getDiscussionsByTag(String tag);
    List<Discussion> getDiscussionsByText(String text);
    void likeDiscussion(Long discussionId, Long userId);
    void viewDiscussion(Long discussionId);
}
