package com.todo.assessment.dto;

import lombok.Data;

@Data
public class TodoDto {

    public Long id;
    public String title;
    public Boolean isCompleted;
}
