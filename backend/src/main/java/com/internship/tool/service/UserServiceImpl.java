package com.internship.tool.service;

import com.internship.tool.entity.User;
import com.internship.tool.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        } else {
            user.setRole(user.getRole().trim().toUpperCase());
        }

        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        System.out.println("Fetching users from DB...");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getUserById(Long id) {
        System.out.println("Fetching user by id from DB...");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    @CacheEvict(value = {"users", "user"}, allEntries = true)
    public User updateUser(Long id, User user) {

        User existingUser = getUserById(id);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getRole() != null && !user.getRole().isBlank()) {
            existingUser.setRole(user.getRole().trim().toUpperCase());
        }

        return userRepository.save(existingUser);
    }

    @Override
    @CacheEvict(value = {"users", "user"}, allEntries = true)
    public void deleteUser(Long id) {
     User user = getUserById(id);
        userRepository.delete(user);
    }
}