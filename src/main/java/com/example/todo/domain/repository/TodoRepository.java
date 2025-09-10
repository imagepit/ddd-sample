package com.example.todo.domain.repository;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoId;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);
    Optional<Todo> findById(TodoId todoId);
    List<Todo> findAll();
    List<Todo> findByCompleted(boolean completed);
    void deleteById(TodoId todoId);
}