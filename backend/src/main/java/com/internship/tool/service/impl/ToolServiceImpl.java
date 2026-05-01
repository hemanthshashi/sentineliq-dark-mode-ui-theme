package com.internship.tool.service.impl;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.entity.Tool;
import com.internship.tool.repository.ToolRepository;
import com.internship.tool.service.EmailService;
import com.internship.tool.service.ToolService;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;
    private final EmailService emailService;

    public ToolServiceImpl(ToolRepository toolRepository, EmailService emailService) {
        this.toolRepository = toolRepository;
        this.emailService = emailService;
    }

    @Override
    public ToolResponse createTool(ToolRequest request) {

        Tool tool = new Tool();
        tool.setName(request.getName());
        tool.setCategory(request.getCategory());
        tool.setDescription(request.getDescription());
        tool.setWebsiteUrl(request.getWebsiteUrl());
        tool.setLogoUrl(request.getLogoUrl());
        tool.setActive(true);

        Tool savedTool = toolRepository.save(tool);

        // 🔥 Email simulation
        emailService.send("Tool created: " + savedTool.getName());

        return mapToResponse(savedTool);
    }

    @Override
    public List<ToolResponse> getAllTools() {
        return toolRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ToolResponse getToolById(Long id) {
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found with id: " + id));

        return mapToResponse(tool);
    }

    @Override
    public ToolResponse updateTool(Long id, ToolRequest request) {

        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found with id: " + id));

        tool.setName(request.getName());
        tool.setCategory(request.getCategory());
        tool.setDescription(request.getDescription());
        tool.setWebsiteUrl(request.getWebsiteUrl());
        tool.setLogoUrl(request.getLogoUrl());

        Tool updatedTool = toolRepository.save(tool);

        return mapToResponse(updatedTool);
    }

    @Override
    public void deleteTool(Long id) {
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found with id: " + id));

        toolRepository.delete(tool);
    }

    @Override
    public Page<ToolResponse> searchTools(
            String name,
            String category,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Tool> toolPage;

        if (name != null && !name.isBlank() && category != null && !category.isBlank()) {
            toolPage = toolRepository
                    .findByNameContainingIgnoreCaseAndCategoryIgnoreCase(name, category, pageable);

        } else if (name != null && !name.isBlank()) {
            toolPage = toolRepository
                    .findByNameContainingIgnoreCase(name, pageable);

        } else if (category != null && !category.isBlank()) {
            toolPage = toolRepository
                    .findByCategoryIgnoreCase(category, pageable);

        } else {
            toolPage = toolRepository.findAll(pageable);
        }

        return toolPage.map(this::mapToResponse);
    }

    // 🔁 Mapper
    private ToolResponse mapToResponse(Tool tool) {

        ToolResponse response = new ToolResponse();

        response.setId(tool.getId());
        response.setName(tool.getName());
        response.setCategory(tool.getCategory());
        response.setDescription(tool.getDescription());
        response.setWebsiteUrl(tool.getWebsiteUrl());
        response.setLogoUrl(tool.getLogoUrl());
        response.setActive(tool.getActive());
        response.setCreatedAt(tool.getCreatedAt());
        response.setUpdatedAt(tool.getUpdatedAt());

        return response;
    }
}