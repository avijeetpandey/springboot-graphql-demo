package com.example.springbootgraphqldemo.service;

import com.example.springbootgraphqldemo.convertor.TodoConverter;
import com.example.springbootgraphqldemo.convertor.TodoDtoConvertor;
import com.example.springbootgraphqldemo.convertor.TodoItemConverter;
import com.example.springbootgraphqldemo.convertor.TodoItemDtoConvertor;
import com.example.springbootgraphqldemo.dto.TodoDto;
import com.example.springbootgraphqldemo.dto.TodoItemDto;
import com.example.springbootgraphqldemo.entity.Todo;
import com.example.springbootgraphqldemo.entity.TodoItem;
import com.example.springbootgraphqldemo.repository.TodoItemRepository;
import com.example.springbootgraphqldemo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoItemRepository todoItemRepository;
    private final TodoDtoConvertor todoDtoConverter;
    private final TodoConverter todoConverter;
    private final TodoItemDtoConvertor todoItemDtoConverter;
    private final TodoItemConverter todoItemConverter;

    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = todoConverter.convert(todoDto);
        Todo savedTodo = todoRepository.save(todo);

        List<TodoItemDto> todoItemDtoList = todoDto.getItems().stream()
                .map(todoItemConverter::convert)
                .map(todoItem -> {
                    todoItem.setTodo(savedTodo);
                    return todoItemDtoConverter.convert(todoItemRepository.save(todoItem));
                }).toList();

        todoDto = todoDtoConverter.convert(savedTodo);
        todoDto.setItems(todoItemDtoList);
        return todoDto;
    }

    public List<TodoItemDto> addTodoItem(long todoId, List<TodoItemDto> todoItemDtoList) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo with id '" + todoId + "'  not found"));

        return todoItemDtoList.stream().map(todoItemDto -> {
            TodoItem todoItem = todoItemConverter.convert(todoItemDto);
            todoItem.setTodo(todo);
            todoItem = todoItemRepository.save(todoItem);
            return todoItemDtoConverter.convert(todoItem);
        }).toList();
    }

    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream().map(todoDtoConverter::convert).toList();
    }

    public TodoItemDto updateTodoItem(TodoItemDto todoItemDto) {
        if (!todoItemRepository.existsById(todoItemDto.getId())) {
            throw new RuntimeException("Todo item with id '" + todoItemDto.getId() + "'  not found");
        }
        todoItemRepository.save(todoItemConverter.convert(todoItemDto));
        return todoItemDto;
    }

}