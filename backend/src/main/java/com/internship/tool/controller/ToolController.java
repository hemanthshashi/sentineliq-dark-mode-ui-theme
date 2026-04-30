package com.internship.tool.controller;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.service.ToolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    // ✅ Constructor injection
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    // ✅ CREATE
    @PostMapping
    public ToolResponse createTool(@RequestBody ToolRequest request) {
        return toolService.createTool(request);
    }

    // ✅ GET ALL
    @GetMapping
    public List<ToolResponse> getAllTools() {
        return toolService.getAllTools();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ToolResponse getToolById(@PathVariable Long id) {
        return toolService.getToolById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ToolResponse updateTool(@PathVariable Long id,
                                   @RequestBody ToolRequest request) {
        return toolService.updateTool(id, request);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return "Tool deleted successfully";
    }
}