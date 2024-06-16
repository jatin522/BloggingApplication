package com.example.BloggingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BloggingApplication.entity.Discussion;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findByHashtagsContaining(String tag);
    List<Discussion> findByTextContaining(String text);
}