package com.example.todo.domain.model;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;
    
    public static Priority fromString(String priority) {
        try {
            return Priority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Priority.MEDIUM;
        }
    }
}