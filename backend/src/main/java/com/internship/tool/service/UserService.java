package com.internship.tool.service;

import com.internship.tool.entity.User;
import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
    throw new RuntimeException("Email already exists");
}
}