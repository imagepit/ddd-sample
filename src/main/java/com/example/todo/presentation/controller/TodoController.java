package com.example.todo.presentation.controller;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.application.dto.TodoDto;
import com.example.todo.application.dto.UpdateTodoCommand;
import com.example.todo.application.service.TodoApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoApplicationService todoApplicationService;
    
    public TodoController(TodoApplicationService todoApplicationService) {
        this.todoApplicationService = todoApplicationService;
    }
    
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody CreateTodoCommand command) {
        TodoDto todo = todoApplicationService.createTodo(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }
    
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(@RequestParam(required = false) Boolean completed) {
        List<TodoDto> todos;
        if (completed != null) {
            todos = todoApplicationService.getTodosByCompleted(completed);
        } else {
            todos = todoApplicationService.getAllTodos();
        }
        return ResponseEntity.ok(todos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable String id) {
        try {
            TodoDto todo = todoApplicationService.getTodoById(id);
            return ResponseEntity.ok(todo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable String id, 
                                             @Valid @RequestBody UpdateTodoCommand command) {
        try {
            TodoDto todo = todoApplicationService.updateTodo(id, command);
            return ResponseEntity.ok(todo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        try {
            todoApplicationService.deleteTodo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}