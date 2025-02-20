package com.example.springbootgraphqldemo.convertor;

import com.example.springbootgraphqldemo.dto.TodoDto;
import com.example.springbootgraphqldemo.entity.Todo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;


@Component
public class TodoConverter implements Converter<TodoDto, Todo> {
    @Override
    public Todo convert(TodoDto source) {
        return new Todo(0,
                "Test Todo",
                false,
               LocalDate.now(),
                Collections.emptyList());
    }
}