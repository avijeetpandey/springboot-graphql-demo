package com.example.springbootgraphqldemo.controller;

import com.example.springbootgraphqldemo.dto.TodoDto;
import com.example.springbootgraphqldemo.dto.TodoItemDto;
import com.example.springbootgraphqldemo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private TodoService todoService;

    @QueryMapping
    public List<TodoDto> getAllTodo() {
        return todoService.getAllTodos();
    }

    @MutationMapping
    public TodoDto createTodo(@Argument(value = "todo") TodoDto todoDto) {
        return todoService.addTodo(todoDto);
    }

    @MutationMapping
    public List<TodoItemDto> addTodoItems(@Argument long todoId,
                                          @Argument List<TodoItemDto> todoItems) {
        return todoService.addTodoItem(todoId, todoItems);
    }

    @MutationMapping
    public TodoItemDto updateTodoItem(@Argument TodoItemDto todoItem) {
        return todoService.updateTodoItem(todoItem);
    }

}