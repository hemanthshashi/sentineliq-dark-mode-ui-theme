package com.internship.tool.service.impl;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.entity.Tool;
import com.internship.tool.repository.ToolRepository;
import com.internship.tool.service.ToolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;

    public ToolServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
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
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        return mapToResponse(tool);
    }

    @Override
    public ToolResponse updateTool(Long id, ToolRequest request) {

        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found"));

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
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        toolRepository.delete(tool);
    }

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