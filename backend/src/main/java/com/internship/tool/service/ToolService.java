package com.internship.tool.service;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;

import java.util.List;

public interface ToolService {

    ToolResponse createTool(ToolRequest request);

    List<ToolResponse> getAllTools();

    ToolResponse getToolById(Long id);

    ToolResponse updateTool(Long id, ToolRequest request);

    void deleteTool(Long id);
}