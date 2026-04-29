package com.internship.tool.service;

import com.internship.tool.entity.User;
import com.internship.tool.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ✅ CREATE USER
    @Override
    public User createUser(User user) {

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // ✅ GET ALL USERS
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ GET USER BY ID
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // ✅ UPDATE USER
    @Override
    public User updateUser(Long id, User user) {

        User existingUser = getUserById(id);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        // Update password only if provided
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Update role if provided
        if (user.getRole() != null && !user.getRole().isEmpty()) {
            existingUser.setRole(user.getRole());
        }

        return userRepository.save(existingUser);
    }

    // ✅ DELETE USER
    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}