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
        createTool("ChatGPT", "AI Tool", "Used for answering questions and coding help", "https://chat.openai.com", "https://logo.clearbit.com/openai.com");
        createTool("Figma", "Design Tool", "Helps in designing UI screens and prototypes", "https://figma.com", "https://logo.clearbit.com/figma.com");
        createTool("Notion", "Productivity", "Used for notes, task tracking and documentation", "https://notion.so", "https://logo.clearbit.com/notion.so");
        createTool("GitHub", "Development", "Used to store and manage project code repositories", "https://github.com", "https://logo.clearbit.com/github.com");
        createTool("Postman", "API Tool", "Used for testing backend APIs during development", "https://postman.com", "https://logo.clearbit.com/postman.com");

        System.out.println("Demo tools seeded successfully.");
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