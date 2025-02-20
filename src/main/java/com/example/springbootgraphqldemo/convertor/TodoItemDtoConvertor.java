package com.example.springbootgraphqldemo.convertor;

import com.example.springbootgraphqldemo.dto.TodoItemDto;
import com.example.springbootgraphqldemo.entity.TodoItem;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TodoItemDtoConvertor implements Converter<TodoItem, TodoItemDto> {

    @Override
    public @NonNull TodoItemDto convert(TodoItem source) {
        return new TodoItemDto(source.getId(), source.getNotes(), source.getDueDate(), source.isMarkAsComplete(), source.getCompletionDate(), source.getCreatedAt(), source.getUpdatedAt());
    }
}