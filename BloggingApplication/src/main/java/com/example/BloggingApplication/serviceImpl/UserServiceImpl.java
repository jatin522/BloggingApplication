package com.example.BloggingApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BloggingApplication.entity.User;
import com.example.BloggingApplication.repository.UserRepository;
import com.example.BloggingApplication.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null; // Handle user not found
        }
        User user = userOptional.get();
        user.setName(userDetails.getName());
        user.setMobileNo(userDetails.getMobileNo());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            // Handle user not found
            return;
        }
        userRepository.delete(userOptional.get());
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsers(String name) {
        return userRepository.findByNameContaining(name);
    }

    @Override
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            return Optional.empty(); // Handle user not found
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            return Optional.empty(); // Handle invalid credentials
        }
        return Optional.of(user);
    }

    @Override
    public void followUser(Long userId, Long followUserId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<User> followUserOptional = userRepository.findById(followUserId);
        if (!userOptional.isPresent() || !followUserOptional.isPresent()) {
            // Handle user or follow user not found
            return;
        }
        User user = userOptional.get();
        User followUser = followUserOptional.get();
        user.getFollowers().add(followUser);
        userRepository.save(user);
    }
}
