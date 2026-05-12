package com.internship.tool.controller;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.service.ToolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin(origins = "*")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public List<ToolResponse> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/all")
    public List<ToolResponse> getAllToolsOldUrl() {
        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    public ToolResponse getToolById(@PathVariable Long id) {
        return toolService.getToolById(id);
    }

    @PostMapping
    public ResponseEntity<ToolResponse> createTool(
            @Valid @RequestBody ToolRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toolService.createTool(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToolResponse> updateTool(
            @PathVariable Long id,
            @Valid @RequestBody ToolRequest request
    ) {
        return ResponseEntity.ok(toolService.updateTool(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return ResponseEntity.ok("Tool deleted successfully");
    }
}