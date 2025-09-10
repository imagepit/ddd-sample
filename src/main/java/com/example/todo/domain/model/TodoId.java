package com.example.todo.domain.model;

import java.util.Objects;
import java.util.UUID;

public record TodoId(String value) {
    
    public TodoId {
        Objects.requireNonNull(value, "TodoId value cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("TodoId value cannot be blank");
        }
    }
    
    public static TodoId generate() {
        return new TodoId(UUID.randomUUID().toString());
    }
    
    public static TodoId from(String value) {
        return new TodoId(value);
    }
}