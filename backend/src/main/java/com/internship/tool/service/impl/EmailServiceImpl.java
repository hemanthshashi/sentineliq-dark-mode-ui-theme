package com.internship.tool.service.impl;

import com.internship.tool.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void send(String message) {
        System.out.println("Email notification: " + message);
    }
}