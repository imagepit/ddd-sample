package com.example.todo.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Todo {
    private final TodoId id;
    private Title title;
    private String description;
    private boolean completed;
    private Priority priority;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Todo(TodoId id, Title title, String description, Priority priority) {
        this.id = Objects.requireNonNull(id, "TodoId cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.description = description;
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null");
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public static Todo create(Title title, String description, Priority priority) {
        return new Todo(TodoId.generate(), title, description, priority);
    }
    
    public void changeTitle(Title newTitle) {
        this.title = Objects.requireNonNull(newTitle, "Title cannot be null");
        this.updatedAt = LocalDateTime.now();
    }
    
    public void changeDescription(String newDescription) {
        this.description = newDescription;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void changePriority(Priority newPriority) {
        this.priority = Objects.requireNonNull(newPriority, "Priority cannot be null");
        this.updatedAt = LocalDateTime.now();
    }
    
    public void complete() {
        this.completed = true;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void incomplete() {
        this.completed = false;
        this.updatedAt = LocalDateTime.now();
    }
    
    public TodoId getId() {
        return id;
    }
    
    public Title getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}