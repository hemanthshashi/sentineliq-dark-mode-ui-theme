package com.internship.tool.service;

import com.internship.tool.dto.ToolRequest;
import com.internship.tool.dto.ToolResponse;
import com.internship.tool.entity.Tool;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.repository.ToolRepository;
import com.internship.tool.service.impl.ToolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToolServiceImplTest {

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private ToolServiceImpl toolService;

    private Tool tool;
    private ToolRequest request;

    @BeforeEach
    void setUp() {
        tool = new Tool();
        tool.setId(1L);
        tool.setName("ChatGPT");
        tool.setCategory("AI Tool");
        tool.setDescription("AI assistant");
        tool.setWebsiteUrl("https://chat.openai.com");
        tool.setLogoUrl("");
        tool.setActive(true);

        request = new ToolRequest();
        request.setName("ChatGPT");
        request.setCategory("AI Tool");
        request.setDescription("AI assistant");
        request.setWebsiteUrl("https://chat.openai.com");
        request.setLogoUrl("");
    }

    @Test
    void createTool_ShouldReturnSavedTool() {
        when(toolRepository.save(any(Tool.class))).thenReturn(tool);

        ToolResponse response = toolService.createTool(request);

        assertNotNull(response);
        assertEquals("ChatGPT", response.getName());
        assertEquals("AI Tool", response.getCategory());

        verify(toolRepository, times(1)).save(any(Tool.class));
    }

    @Test
    void getAllTools_ShouldReturnToolList() {
        when(toolRepository.findAll()).thenReturn(List.of(tool));

        List<ToolResponse> response = toolService.getAllTools();

        assertEquals(1, response.size());
        assertEquals("ChatGPT", response.get(0).getName());

        verify(toolRepository, times(1)).findAll();
    }

    @Test
    void getToolById_WhenToolExists_ShouldReturnTool() {
        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        ToolResponse response = toolService.getToolById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("ChatGPT", response.getName());

        verify(toolRepository, times(1)).findById(1L);
    }

    @Test
    void getToolById_WhenToolNotFound_ShouldThrowException() {
        when(toolRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> toolService.getToolById(99L));

        verify(toolRepository, times(1)).findById(99L);
    }

    @Test
    void updateTool_WhenToolExists_ShouldReturnUpdatedTool() {
        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));
        when(toolRepository.save(any(Tool.class))).thenReturn(tool);

        request.setName("Updated Tool");

        ToolResponse response = toolService.updateTool(1L, request);

        assertEquals("Updated Tool", response.getName());

        verify(toolRepository, times(1)).findById(1L);
        verify(toolRepository, times(1)).save(any(Tool.class));
    }

    @Test
    void updateTool_WhenToolNotFound_ShouldThrowException() {
        when(toolRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> toolService.updateTool(99L, request));

        verify(toolRepository, times(1)).findById(99L);
        verify(toolRepository, never()).save(any(Tool.class));
    }

    @Test
    void deleteTool_WhenToolExists_ShouldDeleteTool() {
        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        toolService.deleteTool(1L);

        verify(toolRepository, times(1)).findById(1L);
        verify(toolRepository, times(1)).delete(tool);
    }

    @Test
    void deleteTool_WhenToolNotFound_ShouldThrowException() {
        when(toolRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> toolService.deleteTool(99L));

        verify(toolRepository, times(1)).findById(99L);
        verify(toolRepository, never()).delete(any(Tool.class));
    }

    @Test
    void searchTools_WithoutFilters_ShouldReturnAllPagedResult() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").ascending());
        Page<Tool> toolPage = new PageImpl<>(List.of(tool), pageable, 1);

        when(toolRepository.findAll(pageable)).thenReturn(toolPage);

        Page<ToolResponse> response = toolService.searchTools(
                null,
                null,
                0,
                5,
                "id",
                "asc"
        );

        assertEquals(1, response.getTotalElements());
        assertEquals("ChatGPT", response.getContent().get(0).getName());

        verify(toolRepository, times(1)).findAll(pageable);
    }
}