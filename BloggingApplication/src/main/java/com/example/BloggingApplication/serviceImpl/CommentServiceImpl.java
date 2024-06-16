package com.example.BloggingApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BloggingApplication.entity.Comment;
import com.example.BloggingApplication.entity.Discussion;
import com.example.BloggingApplication.entity.User;
import com.example.BloggingApplication.repository.CommentRepository;
import com.example.BloggingApplication.repository.DiscussionRepository;
import com.example.BloggingApplication.repository.UserRepository;
import com.example.BloggingApplication.service.CommentService;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment addComment(Long discussionId, Comment comment) {
        Optional<Discussion> discussionOptional = discussionRepository.findById(discussionId);
        if (!discussionOptional.isPresent()) {
            return null; // Handle discussion not found
        }
        Discussion discussion = discussionOptional.get();
        comment.setDiscussion(discussion);
        return commentRepository.save(comment);
    }
          
    @Override
    public Comment updateComment(Long id, Comment commentDetails) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()) {
            return null; // Handle comment not found
        }
        Comment comment = commentOptional.get();
        comment.setText(commentDetails.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()) {
            // Handle comment not found
            return;
        }
        commentRepository.delete(commentOptional.get());
    }

    @Override
    public List<Comment> listComments(Long discussionId) {
        return commentRepository.findByDiscussionId(discussionId);
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (!commentOptional.isPresent() || !userOptional.isPresent()) {
            // Handle comment or user not found
            return;
        }
        Comment comment = commentOptional.get();
        User user = userOptional.get();
        comment.getLikes().add(user);
        commentRepository.save(comment);
    }

    @Override
    public void replyToComment(Long commentId, Comment reply) {
        Optional<Comment> parentCommentOptional = commentRepository.findById(commentId);
        if (!parentCommentOptional.isPresent()) {
            return; // Handle parent comment not found
        }
        Comment parentComment = parentCommentOptional.get();
        reply.setParentComment(parentComment);
        commentRepository.save(reply);
    }
}
