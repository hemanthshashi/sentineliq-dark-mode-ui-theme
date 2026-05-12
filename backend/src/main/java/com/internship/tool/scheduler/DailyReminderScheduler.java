package com.internship.tool.scheduler;

import com.internship.tool.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyReminderScheduler {

    private final EmailService emailService;

    public DailyReminderScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    // For testing: runs every 1 minute
    @Scheduled(fixedRate = 60000)
    public void sendDailyReminder() {
        emailService.sendDailyReminder(
                "your-email@gmail.com",
                "Hemanth"
        );

        System.out.println("Daily reminder scheduled email triggered");
    }

    // For testing: runs every 2 minutes
    @Scheduled(fixedRate = 120000)
    public void sendDeadlineAlert() {
        emailService.sendDeadlineAlert(
                "your-email@gmail.com",
                "Hemanth",
                "Today 11:59 PM"
        );

        System.out.println("Deadline alert scheduled email triggered");
    }
}