package com.internship.tool.controller;

import com.internship.tool.entity.User;
import com.internship.tool.repository.UserRepository;
import com.internship.tool.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
 private JwtService jwtService; 

   @PostMapping("/register")
public String register(@RequestBody User user) {

    // encode password
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // default role if not provided
    if (user.getRole() == null || user.getRole().trim().isEmpty()) {
        user.setRole("USER");
    }

    userRepository.save(user);

    return "User Registered Successfully";
}
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return jwtService.generateToken(user.getEmail());
        }

        throw new RuntimeException("Invalid Password");
    }
}