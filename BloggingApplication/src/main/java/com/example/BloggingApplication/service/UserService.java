package com.example.BloggingApplication.service;

import java.util.List;
import java.util.Optional;

import com.example.BloggingApplication.entity.User;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    List<User> listUsers();
    List<User> searchUsers(String name);
    Optional<User> login(String email, String password);
    void followUser(Long userId, Long followUserId);
}
