package com.example.todo.application.dto;

import com.example.todo.domain.model.Priority;
import com.example.todo.domain.model.Todo;

import java.time.LocalDateTime;

public record TodoDto(
        String id,
        String title,
        String description,
        boolean completed,
        Priority priority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TodoDto from(Todo todo) {
        return new TodoDto(
                todo.getId().value(),
                todo.getTitle().value(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getPriority(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}