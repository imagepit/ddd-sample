package com.example.todo.domain.service;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoId;
import com.example.todo.domain.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    public boolean existsById(TodoId todoId) {
        return todoRepository.findById(todoId).isPresent();
    }
    
    public void validateTodoExists(TodoId todoId) {
        if (!existsById(todoId)) {
            throw new IllegalArgumentException("Todo not found: " + todoId.value());
        }
    }
}