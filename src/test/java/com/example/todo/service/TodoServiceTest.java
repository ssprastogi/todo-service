package com.example.todo.service;

import com.example.todo.TodoException;
import com.example.todo.dao.TodoRepository;
import com.example.todo.entity.Todo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoServiceTest {

    TodoService todoService;
    @Mock
    TodoRepository todoRepository;
    Todo todo;

    @PostConstruct
    public void setup() {
        todoService = new TodoService(todoRepository);
        todo = Todo.builder()
                .id(1)
                .description("desc")
                .completionStatus("active")
                .build();
        todoService.addTodo(todo);
    }

    @Test
    public void getTodoById() throws TodoException {
        when(todoRepository.findById(1))
                .thenReturn(todo);
        Todo todoById = todoService.getTodoById(1);
        assertEquals(todoById.getId(), todo.getId());
    }

    @Test
    public void findAllTodos() {
        when(todoRepository.findAll())
                .thenReturn(Collections.singletonList(todo));
        List<Todo> actualTodos = todoService.findAllTodos();

        assertEquals(actualTodos.size(), 1);
    }

    @Test
    public void addTodo() {
        Todo todo = Todo.builder()
                .id(2)
                .description("desc2")
                .completionStatus("active2")
                .build();
        when(todoRepository.save(any(Todo.class)))
                .thenReturn(todo);
        Todo actualTodo = todoService.addTodo(todo);
        assertEquals(actualTodo, todo);

    }


    public void updateTodo() throws TodoException {
        when(todoRepository.save(todo))
                .thenReturn(todo);

        todoService.updateTodo(todo);
    }


    public void deleteTodo() throws TodoException {
        doNothing().when(todoRepository).deleteById(2);

        todoService.deleteTodo(2);
    }
}
