package com.example.BloggingApplication.entity;
 
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String image;
    @ElementCollection
    private List<String> hashtags;
    private LocalDateTime createdOn;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "discussion")
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    private Set<User> likes = new HashSet<>();

    private int viewCount;
}