package com.internship.tool.service;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.entity.Tool;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.repository.ToolRepository;
import com.internship.tool.service.impl.ToolServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToolServiceImplTest {

    private final ToolRepository toolRepository = mock(ToolRepository.class);
    private final EmailService emailService = mock(EmailService.class);
    private final ToolServiceImpl toolService =
            new ToolServiceImpl(toolRepository, emailService);

    private ToolRequest createRequest() {
        ToolRequest request = new ToolRequest();
        request.setName("ChatGPT");
        request.setCategory("AI");
        request.setDescription("AI assistant");
        request.setWebsiteUrl("https://chat.openai.com");
        request.setLogoUrl("logo.png");
        return request;
    }

    private Tool createTool() {
        Tool tool = new Tool();
        tool.setId(1L);
        tool.setName("ChatGPT");
        tool.setCategory("AI");
        tool.setDescription("AI assistant");
        tool.setWebsiteUrl("https://chat.openai.com");
        tool.setLogoUrl("logo.png");
        tool.setActive(true);
        return tool;
    }

    @Test
    void createTool_ShouldReturnToolResponse() {
        ToolRequest request = createRequest();
        Tool savedTool = createTool();

        when(toolRepository.save(any(Tool.class))).thenReturn(savedTool);

        ToolResponse response = toolService.createTool(request);

        assertNotNull(response);
        assertEquals("ChatGPT", response.getName());
        verify(emailService, times(1)).send("Tool created: ChatGPT");
    }

    @Test
    void getToolById_ShouldReturnTool_WhenExists() {
        Tool tool = createTool();

        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        ToolResponse response = toolService.getToolById(1L);

        assertEquals(1L, response.getId());
        assertEquals("ChatGPT", response.getName());
    }

    @Test
    void getToolById_ShouldThrowException_WhenNotFound() {
        when(toolRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> toolService.getToolById(99L));
    }

    @Test
    void updateTool_ShouldReturnUpdatedTool_WhenExists() {
        Tool existingTool = createTool();

        ToolRequest updateRequest = new ToolRequest();
        updateRequest.setName("ChatGPT Updated");
        updateRequest.setCategory("AI Updated");
        updateRequest.setDescription("Updated description");
        updateRequest.setWebsiteUrl("https://updated.com");
        updateRequest.setLogoUrl("updated-logo.png");

        when(toolRepository.findById(1L)).thenReturn(Optional.of(existingTool));
        when(toolRepository.save(any(Tool.class))).thenReturn(existingTool);

        ToolResponse response = toolService.updateTool(1L, updateRequest);

        assertEquals("ChatGPT Updated", response.getName());
        assertEquals("AI Updated", response.getCategory());
    }

    @Test
    void updateTool_ShouldThrowException_WhenNotFound() {
        ToolRequest updateRequest = createRequest();

        when(toolRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> toolService.updateTool(99L, updateRequest));
    }

    @Test
    void deleteTool_ShouldDelete_WhenExists() {
        Tool tool = createTool();

        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        toolService.deleteTool(1L);

        verify(toolRepository, times(1)).delete(tool);
    }

    @Test
    void deleteTool_ShouldThrowException_WhenNotFound() {
        when(toolRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> toolService.deleteTool(99L));
    }
}