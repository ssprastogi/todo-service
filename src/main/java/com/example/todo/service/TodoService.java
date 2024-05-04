package com.example.todo.service;

import com.example.todo.TodoException;
import com.example.todo.dao.TodoRepository;
import com.example.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public Todo getTodoById(int id) throws TodoException {
        Todo todoById = todoRepository.findById(id);
        if (null == todoById) {
            throw new TodoException("No record found for given Id", HttpStatus.NOT_FOUND);
        }
        return todoById;
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) throws TodoException {
        Todo todoById = todoRepository.findById(todo.getId());
        if (null != todoById) {
            return todoRepository.save(todo);
        } else {
            throw new TodoException("No record found to update", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteTodo(int id) throws TodoException {
        Todo todoById = todoRepository.findById(id);
        if (null != todoById) {
            todoRepository.deleteById(id);
        } else {
            throw new TodoException("No record found to delete", HttpStatus.NOT_FOUND);
        }

    }
}
