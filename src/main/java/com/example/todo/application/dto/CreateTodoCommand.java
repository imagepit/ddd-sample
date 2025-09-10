package com.example.todo.application.dto;

import com.example.todo.domain.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTodoCommand(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title cannot exceed 100 characters")
        String title,
        
        String description,
        
        @NotNull(message = "Priority is required")
        Priority priority
) {
}