package com.internship.tool.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendDailyReminder(String toEmail, String name) {
        Context context = new Context();
        context.setVariable("name", name);

        String htmlBody = templateEngine.process("daily-reminder", context);

        sendHtmlEmail(
                toEmail,
                "Daily Reminder - Tool Management Project",
                htmlBody
        );
    }

    public void sendDeadlineAlert(String toEmail, String name, String deadline) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("deadline", deadline);

        String htmlBody = templateEngine.process("deadline-alert", context);

        sendHtmlEmail(
                toEmail,
                "Deadline Alert - CampusPe Internship",
                htmlBody
        );
    }

    private void sendHtmlEmail(String toEmail, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);

            System.out.println("Email sent successfully to: " + toEmail);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}