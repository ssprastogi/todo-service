package com.example.todo.controller;

import com.example.todo.TodoException;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class TodoController {
    Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getTodos() {
        LOGGER.info("getTodos called...");
        return new ResponseEntity<>(todoService.findAllTodos(), HttpStatus.OK);

    }

    @GetMapping(value = "/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> getTodo(@PathVariable int id) throws TodoException {
        LOGGER.info("getTodo called...");
        Todo todoById = todoService.getTodoById(id);
        return new ResponseEntity<>(todoById, HttpStatus.OK);

    }

    @PostMapping(value = "/todos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {

        Todo todo1 = todoService.addTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    @PutMapping(value = "/todos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) throws TodoException {

        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);

    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) throws TodoException {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
