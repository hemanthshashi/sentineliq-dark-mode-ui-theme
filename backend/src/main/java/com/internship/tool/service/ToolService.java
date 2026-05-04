package com.internship.tool.service;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ToolService {

    ToolResponse createTool(ToolRequest request);

    List<ToolResponse> getAllTools();

    ToolResponse getToolById(Long id);

    ToolResponse updateTool(Long id, ToolRequest request);

    void deleteTool(Long id);

    Page<ToolResponse> searchTools(
            String name,
            String category,
            int page,
            int size,
            String sortBy,
            String direction
    );
}