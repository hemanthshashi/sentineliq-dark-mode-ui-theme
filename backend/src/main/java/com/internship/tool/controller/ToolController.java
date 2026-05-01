package com.internship.tool.controller;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.service.ToolService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin(origins = "http://localhost:5173")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @PostMapping
    public ToolResponse createTool(@RequestBody ToolRequest request) {
        return toolService.createTool(request);
    }

    @GetMapping
    public List<ToolResponse> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    public ToolResponse getToolById(@PathVariable Long id) {
        return toolService.getToolById(id);
    }

    @PutMapping("/{id}")
    public ToolResponse updateTool(@PathVariable Long id, @RequestBody ToolRequest request) {
        return toolService.updateTool(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return "Tool deleted successfully";
    }

    @GetMapping("/search")
    public Page<ToolResponse> searchTools(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return toolService.searchTools(name, category, page, size, sortBy, direction);
    }
}