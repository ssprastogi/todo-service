package com.example.todo.dao;

import com.example.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public List<Todo> findAll();

    public Todo findById(int id);

    public Todo save(Todo todo);

    public void deleteById(int id);

}
