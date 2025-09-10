package com.example.todo.domain.model;

import java.util.Objects;

public record Title(String value) {
    
    public Title {
        Objects.requireNonNull(value, "Title cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }
    }
    
    public static Title of(String value) {
        return new Title(value);
    }
}