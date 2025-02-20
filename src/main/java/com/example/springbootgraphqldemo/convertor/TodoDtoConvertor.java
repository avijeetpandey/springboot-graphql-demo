package com.example.springbootgraphqldemo.convertor;

import com.example.springbootgraphqldemo.dto.TodoDto;
import com.example.springbootgraphqldemo.dto.TodoItemDto;
import com.example.springbootgraphqldemo.entity.Todo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class TodoDtoConvertor implements Converter<Todo, TodoDto> {

    private final TodoItemDtoConvertor todoItemDtoConverter;

    @Override
    public @NonNull TodoDto convert(Todo source) {
        List<TodoItemDto> todoItems = source.getItems().stream().map(todoItemDtoConverter::convert).toList();
        return new TodoDto(source.getId(), source.getTitle(), source.isMarkAsComplete(), todoItems);
    }
}