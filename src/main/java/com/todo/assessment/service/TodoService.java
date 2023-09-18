package com.todo.assessment.service;
import com.todo.assessment.dto.TodoDto;
import com.todo.assessment.dto.entity.ToDo;

import java.util.List;
import java.util.Optional;


public interface TodoService {
    ToDo addTodo(TodoDto toDo);

   Optional<ToDo> getTodo(Long id);

    Optional<List<ToDo>> getAllTodo(Boolean isCompleted);

    public void deleteTodo(Long id);

}
