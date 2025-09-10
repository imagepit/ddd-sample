package com.example.todo.application.service;

import com.example.todo.application.dto.CreateTodoCommand;
import com.example.todo.application.dto.TodoDto;
import com.example.todo.application.dto.UpdateTodoCommand;
import com.example.todo.domain.model.Priority;
import com.example.todo.domain.model.Title;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoId;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.service.TodoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoApplicationService {
    private final TodoRepository todoRepository;
    private final TodoService todoService;
    
    public TodoApplicationService(TodoRepository todoRepository, TodoService todoService) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
    }
    
    public TodoDto createTodo(CreateTodoCommand command) {
        Todo todo = Todo.create(
                Title.of(command.title()),
                command.description(),
                command.priority()
        );
        Todo savedTodo = todoRepository.save(todo);
        return TodoDto.from(savedTodo);
    }
    
    @Transactional(readOnly = true)
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(TodoDto::from)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public TodoDto getTodoById(String id) {
        TodoId todoId = TodoId.from(id);
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
        return TodoDto.from(todo);
    }
    
    @Transactional(readOnly = true)
    public List<TodoDto> getTodosByCompleted(boolean completed) {
        return todoRepository.findByCompleted(completed).stream()
                .map(TodoDto::from)
                .collect(Collectors.toList());
    }
    
    public TodoDto updateTodo(String id, UpdateTodoCommand command) {
        TodoId todoId = TodoId.from(id);
        todoService.validateTodoExists(todoId);
        
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
        
        if (command.title() != null && !command.title().isBlank()) {
            todo.changeTitle(Title.of(command.title()));
        }
        
        if (command.description() != null) {
            todo.changeDescription(command.description());
        }
        
        if (command.priority() != null) {
            todo.changePriority(command.priority());
        }
        
        if (command.completed() != null) {
            if (command.completed()) {
                todo.complete();
            } else {
                todo.incomplete();
            }
        }
        
        Todo updatedTodo = todoRepository.save(todo);
        return TodoDto.from(updatedTodo);
    }
    
    public void deleteTodo(String id) {
        TodoId todoId = TodoId.from(id);
        todoService.validateTodoExists(todoId);
        todoRepository.deleteById(todoId);
    }
}