package com.example.todo.application.dto;

import com.example.todo.domain.model.Priority;
import jakarta.validation.constraints.Size;

public record UpdateTodoCommand(
        @Size(max = 100, message = "Title cannot exceed 100 characters")
        String title,
        
        String description,
        
        Priority priority,
        
        Boolean completed
) {
}