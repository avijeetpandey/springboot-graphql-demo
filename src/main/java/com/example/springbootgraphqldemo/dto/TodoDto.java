package com.example.springbootgraphqldemo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoDto {
    private Long id;
    private String title;
    private boolean isMarkAsComplete = false;

    private List<TodoItemDto> items = new ArrayList<>();
}