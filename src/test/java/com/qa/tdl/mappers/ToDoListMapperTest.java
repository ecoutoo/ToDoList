package com.qa.tdl.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdl.data.model.ToDoList;
import com.qa.tdl.dto.ToDoListDTO;

@SpringBootTest
public class ToDoListMapperTest {

	@Autowired
	private ToDoListMapper toDoListMapper;
	
	private ToDoList toDoList;
	private ToDoListDTO toDoListDTO;
	
	@BeforeEach
	public void init() {
		toDoList = new ToDoList("test", true);
		toDoListDTO = new ToDoListDTO("test", true);
	}
	
	@Test
	public void mapToDTOTest() {
		assertThat(toDoListDTO).isEqualTo(toDoListMapper.mapToDTO(toDoList));
	}
	
	@Test
	public void mapToTaskTest() {
		assertThat(toDoList).isEqualTo(toDoListMapper.mapToToDoList(toDoListDTO));
	}
	
}
