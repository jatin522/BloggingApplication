package com.example.BloggingApplication.service;

import java.util.List;

import com.example.BloggingApplication.entity.Comment;

public interface CommentService {
    Comment addComment(Long discussionId, Comment comment);
    Comment updateComment(Long id, Comment commentDetails);
    void deleteComment(Long id);
    List<Comment> listComments(Long discussionId);
    void likeComment(Long commentId, Long userId);
    void replyToComment(Long commentId, Comment reply);
}
