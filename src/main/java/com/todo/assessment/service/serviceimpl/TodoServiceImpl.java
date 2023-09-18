package com.todo.assessment.service.serviceimpl;

import com.todo.assessment.dto.TodoDto;
import com.todo.assessment.dto.entity.ToDo;
import com.todo.assessment.repository.TodoRepository;
import com.todo.assessment.service.TodoService;
import com.todo.assessment.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public ToDo addTodo(TodoDto todoDto) {
    /*Here I am manually setting dto to dao object but if we are using any no sql like mongodb then we can create
    entity using @Document and we don't need separate DTO or we can go with mapstruct as well*/
        ToDo toDo = new ToDo();
        toDo.setIsCompleted(todoDto.getIsCompleted());
        toDo.setTittle(todoDto.getTitle());
        toDo.setCreatedAt(DateUtil.convertToStringDate(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
        return todoRepository.save(toDo);
    }

    @Override
    public Optional<ToDo> getTodo(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Optional<List<ToDo>> getAllTodo(Boolean isCompleted) {
        Optional<List<ToDo>> filteredList = Optional.of(todoRepository.findAll());
        return (filteredList.isPresent() && !filteredList.get().isEmpty()) ? Optional.of(filteredList.get().stream().filter(todo -> Boolean.compare(isCompleted, todo.isCompleted) == 0).collect(Collectors.toList())) : filteredList;
    }

    @Override
    public void deleteTodo(Long id) {
        log.info("Deleting Todo Item for ID:{}", id);
        todoRepository.deleteById(id);
    }
}
