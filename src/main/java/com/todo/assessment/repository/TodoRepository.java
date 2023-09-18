package com.todo.assessment.repository;

import com.todo.assessment.dto.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<ToDo, Long> {
    // You can add custom queries if needed
}
