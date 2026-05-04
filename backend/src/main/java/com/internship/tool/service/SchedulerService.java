package com.internship.tool.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    // Runs every 1 minute
    @Scheduled(fixedRate = 60000)
    public void runTask() {
        System.out.println("Scheduled task running...");
    }
}