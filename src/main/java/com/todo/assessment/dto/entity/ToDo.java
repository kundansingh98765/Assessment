package com.todo.assessment.dto.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class ToDo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;
        @Column(name = "createdTimestamp")
        public String createdAt;

        @Column(name = "title")
        public String tittle;

        @Column(name = "isCompleted")
        public Boolean isCompleted;



}
