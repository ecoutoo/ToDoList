package com.qa.tdl.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdl.data.model.ToDoList;
import com.qa.tdl.data.model.Item;
import com.qa.tdl.dto.ToDoListDTO;
import com.qa.tdl.dto.ItemDTO;
import com.qa.tdl.mappers.ToDoListMapper;
import com.qa.tdl.data.repository.ToDoListRepository;
import com.qa.tdl.data.repository.ItemRepository;
import com.qa.tdl.services.ToDoListService;

@SpringBootTest
public class ToDoListServiceIntegrationTest {
	
	@Autowired
	private ToDoListService toDoListService;
	
	@Autowired
	private ToDoListRepository toDoListRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToDoListMapper toDoListMapper;
	
	private List<ToDoList> toDoLists;
	private List<ToDoListDTO> toDoListDTOs;
	
	private ToDoList validToDoList;
	private ToDoListDTO validToDoListDTO;
	
	private Item validItem;
	private ItemDTO validItemDTO;
	
	@BeforeEach
	public void init() {
		validToDoList = new ToDoList("TestList", true);
		
		validItem = new Item("TestItem", true);
		validItem.setToDoList(new ToDoList(1, "TestList", true));
		
		toDoListRepository.deleteAll();
		toDoListRepository.flush();
		validToDoList = toDoListRepository.saveAndFlush(validToDoList);
		validItem.setToDoList(validToDoList);
		validItem = itemRepository.saveAndFlush(validItem);
		
		validItemDTO = new ItemDTO(validItem.getItemId(), "TestItem", true);
		validToDoListDTO = new ToDoListDTO(validToDoList.getListId(), "TestList", true, List.of(validItemDTO));
	
		toDoLists = List.of(validToDoList);
		toDoListDTOs = List.of(validToDoListDTO);
	}
	
	@Test
	public void readAllToDoListsTest() {
		List<ToDoListDTO> toDoListToTest = toDoListService.checkAllToDoLists();
		assertThat(toDoListDTOs).isEqualTo(toDoListToTest);
	}
	
	/*
	@Test
	public void createToDoListTest() {
		ToDoList newToDoList = new ToDoList(1, "TestList", true);
		ToDoListDTO expectedToDoListDTO = toDoListMapper.mapToDTO(newToDoList);
		
		ToDoListDTO savedToDoList = toDoListService.createToDoList(newToDoList);
		expectedToDoListDTO.setListId(savedToDoList.getListId());
		
		assertThat(savedToDoList).isEqualTo(expectedToDoListDTO);
	}
	
	@Test
	public void deleteToDoListTest() {
		assertThat(toDoListService.deleteToDoList(1)).isEqualTo(true);
	}
	*/
	
}
