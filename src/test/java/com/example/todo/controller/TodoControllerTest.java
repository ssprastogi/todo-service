package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    TodoController todoController;

    @Mock
    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        todoController = new TodoController(todoService);
    }

    @Test
    public void getTodos() throws Exception {

        when(todoService.findAllTodos())
                .thenReturn(Collections.singletonList(Todo.builder().build()));
        mockMvc.perform(
                get("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }



    public void getTodo() throws Exception {
        when(todoService.getTodoById(1))
                .thenReturn(new Todo(1, "desc", "test"));
        mockMvc.perform(
                get("/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void addTodo() throws Exception {
        Todo todo = Todo.builder()
                .id(2)
                .description("desc2")
                .completionStatus("acive2")
                .build();

        when(todoService.addTodo(todo))
                .thenReturn(todo);
        mockMvc.perform(
                post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"id\":2,\n" +
                                "\"description\":\"desc2\",\n" +
                                "\"completionStatus\":\"acive2\"\n" +
                                "}")
        ).andExpect(status().isCreated());
    }

    @Test
    public void updateTodo() throws Exception {
        Todo todo = Todo.builder()
                .id(2)
                .description("desc2")
                .completionStatus("active")
                .build();
        when(todoService.updateTodo(todo))
                .thenReturn(todo);
        mockMvc.perform(
                put("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"id\":2,\n" +
                                "\"description\":\"desc2\",\n" +
                                "\"completionStatus\":\"active\"\n" +
                                "}")
        ).andExpect(status().isOk());
    }


    public void deleteTodo() throws Exception {
        doNothing().when(todoService).deleteTodo(1);

        mockMvc.perform(
                delete("/todos/1")
        ).andExpect(status().isNoContent());
    }

}
