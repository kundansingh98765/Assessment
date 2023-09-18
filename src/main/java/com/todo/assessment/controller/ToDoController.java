package com.todo.assessment.controller;

import com.todo.assessment.dto.TodoDto;
import com.todo.assessment.dto.entity.ToDo;
import com.todo.assessment.service.TodoService;
import com.todo.assessment.dto.Response;
import com.todo.assessment.utils.UriMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UriMapping.BASE_URI)
public class ToDoController {

    @Autowired
    TodoService todoService;


    @PostMapping(UriMapping.ADD_TODO)
    public ResponseEntity<Response> addTodo(@RequestBody TodoDto toDo) {
        ToDo response = todoService.addTodo(toDo);
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.value(), "To Do created", false, response),
                HttpStatus.CREATED);
    }

    @GetMapping(UriMapping.GET_TODO)
    public ResponseEntity<Response> getTodoById(@PathVariable(value = "id") Long id) {
        Response apiResponse;
        if (todoService.getTodo(id).isPresent()) {
            apiResponse = new Response(HttpStatus.OK.value(), "Todo found", false, todoService.getTodo(id).get());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse = new Response(HttpStatus.BAD_REQUEST.value(), "Invalid id, could not find Todo", true, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UriMapping.GET_ALL_TODO)
    public ResponseEntity<Response> getAllTodoByFlag(@RequestParam Boolean isCompleted) {
        List<ToDo> res = todoService.getAllTodo(isCompleted).get();
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.value(), "Here is the Todo list", false, res),
                HttpStatus.OK);
    }


    @DeleteMapping(UriMapping.DELETE_TODO)
    public ResponseEntity<Response> deleteById(@PathVariable(value = "id") Long id) {
        Response apiResponse;
        if (todoService.getTodo(id).isPresent()) {
            todoService.deleteTodo(id);
            apiResponse = new Response(HttpStatus.OK.value(), "Todo deleted", false, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse = new Response(HttpStatus.BAD_REQUEST.value(), "Invalid id, could not delete Todo", true, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }


}
