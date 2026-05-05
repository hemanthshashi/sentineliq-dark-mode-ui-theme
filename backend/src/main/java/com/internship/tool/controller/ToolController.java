package com.internship.tool.controller;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.entity.Tool;
import com.internship.tool.service.ToolService;

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
       public ResponseEntity<ToolResponse> createTool(@RequestBody ToolRequest request) {
       return ResponseEntity.ok(toolService.createTool(request));
     }     

     @PutMapping("/{id}")
      public ResponseEntity<ToolResponse> updateTool(
        @PathVariable Long id,
        @RequestBody ToolRequest request) {
    return ResponseEntity.ok(toolService.updateTool(id, request));
       }
    @DeleteMapping("/{id}")
    public String deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return "Tool deleted successfully";
    }
}