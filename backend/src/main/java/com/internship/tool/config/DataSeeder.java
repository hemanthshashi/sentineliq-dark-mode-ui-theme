package com.internship.tool.config;

import com.internship.tool.entity.Tool;
import com.internship.tool.repository.ToolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ToolRepository toolRepository;

    public DataSeeder(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public void run(String... args) {

        if (toolRepository.count() > 0) {
            return;
        }

        // AI Tools
        createTool("ChatGPT", "AI Tool", "AI assistant for coding and queries", "https://chat.openai.com", "https://logo.clearbit.com/openai.com");
        createTool("Claude", "AI Tool", "AI assistant for writing and analysis", "https://claude.ai", "https://logo.clearbit.com/anthropic.com");
        createTool("Gemini", "AI Tool", "Google AI assistant", "https://gemini.google.com", "https://logo.clearbit.com/google.com");
        createTool("Perplexity", "AI Tool", "AI search engine", "https://perplexity.ai", "https://logo.clearbit.com/perplexity.ai");
        createTool("Midjourney", "AI Tool", "AI image generation tool", "https://midjourney.com", "https://logo.clearbit.com/midjourney.com");

        // Development Tools
        createTool("GitHub", "Development", "Code hosting platform", "https://github.com", "https://logo.clearbit.com/github.com");
        createTool("GitLab", "Development", "DevOps platform", "https://gitlab.com", "https://logo.clearbit.com/gitlab.com");
        createTool("Bitbucket", "Development", "Repository management tool", "https://bitbucket.org", "https://logo.clearbit.com/bitbucket.org");
        createTool("VS Code", "Development", "Code editor by Microsoft", "https://code.visualstudio.com", "https://logo.clearbit.com/microsoft.com");
        createTool("IntelliJ", "Development", "Java IDE", "https://jetbrains.com", "https://logo.clearbit.com/jetbrains.com");

        // Design Tools
        createTool("Figma", "Design", "UI/UX design tool", "https://figma.com", "https://logo.clearbit.com/figma.com");
        createTool("Adobe XD", "Design", "UI design tool", "https://adobe.com", "https://logo.clearbit.com/adobe.com");
        createTool("Canva", "Design", "Graphic design tool", "https://canva.com", "https://logo.clearbit.com/canva.com");
        createTool("Sketch", "Design", "Design tool for macOS", "https://sketch.com", "https://logo.clearbit.com/sketch.com");
        createTool("Framer", "Design", "Interactive UI design tool", "https://framer.com", "https://logo.clearbit.com/framer.com");

        // Productivity Tools
        createTool("Notion", "Productivity", "Notes and task management", "https://notion.so", "https://logo.clearbit.com/notion.so");
        createTool("Trello", "Productivity", "Task management board", "https://trello.com", "https://logo.clearbit.com/trello.com");
        createTool("Slack", "Productivity", "Team communication tool", "https://slack.com", "https://logo.clearbit.com/slack.com");
        createTool("ClickUp", "Productivity", "Project management tool", "https://clickup.com", "https://logo.clearbit.com/clickup.com");
        createTool("Asana", "Productivity", "Work tracking tool", "https://asana.com", "https://logo.clearbit.com/asana.com");

        // API & Backend Tools
        createTool("Postman", "API Tool", "API testing platform", "https://postman.com", "https://logo.clearbit.com/postman.com");
        createTool("Swagger", "API Tool", "API documentation tool", "https://swagger.io", "https://logo.clearbit.com/swagger.io");
        createTool("Insomnia", "API Tool", "REST API client", "https://insomnia.rest", "https://logo.clearbit.com/insomnia.rest");
        createTool("Hoppscotch", "API Tool", "Open-source API testing tool", "https://hoppscotch.io", "https://logo.clearbit.com/hoppscotch.io");
        createTool("RapidAPI", "API Tool", "API marketplace", "https://rapidapi.com", "https://logo.clearbit.com/rapidapi.com");

        // Database Tools
        createTool("MySQL", "Database", "Relational database", "https://mysql.com", "https://logo.clearbit.com/mysql.com");
        createTool("PostgreSQL", "Database", "Advanced relational DB", "https://postgresql.org", "https://logo.clearbit.com/postgresql.org");
        createTool("MongoDB", "Database", "NoSQL database", "https://mongodb.com", "https://logo.clearbit.com/mongodb.com");
        createTool("Redis", "Database", "In-memory database", "https://redis.io", "https://logo.clearbit.com/redis.io");
        createTool("Firebase", "Database", "Backend-as-a-service", "https://firebase.google.com", "https://logo.clearbit.com/firebase.google.com");

        System.out.println("✅ 30 Demo Tools Seeded Successfully");
    }

    private void createTool(String name, String category, String description, String websiteUrl, String logoUrl) {
        Tool tool = new Tool();
        tool.setName(name);
        tool.setCategory(category);
        tool.setDescription(description);
        tool.setWebsiteUrl(websiteUrl);
        tool.setLogoUrl(logoUrl);
        tool.setActive(true);

        toolRepository.save(tool);
    }
}