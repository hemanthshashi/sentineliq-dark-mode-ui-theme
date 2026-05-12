package com.internship.tool.dto;
import jakarta.validation.constraints.NotBlank;


public class ToolRequest {

  @NotBlank(message = "Tool name is required")
 private String name;

 @NotBlank(message = "Category is required")
 private String category; 
    private String description;
    private String websiteUrl;
    private String logoUrl;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}