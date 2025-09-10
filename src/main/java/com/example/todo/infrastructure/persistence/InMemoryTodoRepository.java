package com.example.todo.infrastructure.persistence;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoId;
import com.example.todo.domain.repository.TodoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryTodoRepository implements TodoRepository {
    private final Map<String, Todo> storage = new ConcurrentHashMap<>();
    
    @Override
    public Todo save(Todo todo) {
        storage.put(todo.getId().value(), todo);
        return todo;
    }
    
    @Override
    public Optional<Todo> findById(TodoId todoId) {
        return Optional.ofNullable(storage.get(todoId.value()));
    }
    
    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public List<Todo> findByCompleted(boolean completed) {
        return storage.values().stream()
                .filter(todo -> todo.isCompleted() == completed)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(TodoId todoId) {
        storage.remove(todoId.value());
    }
}