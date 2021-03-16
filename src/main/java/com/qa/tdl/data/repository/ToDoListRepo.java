package com.qa.tdl.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.data.model.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Integer>{

}
