package com.example.BloggingApplication.entity;

import lombok.Data; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Discussion discussion;
    @ManyToOne
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment")
    private Set<Comment> replies = new HashSet<>();

    @ManyToMany
    private Set<User> likes = new HashSet<>();
}
