package com.qa.tdl.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.data.model.ToDoList;
import com.qa.tdl.data.repository.ToDoListRepository;
import com.qa.tdl.dto.ToDoListDTO;
import com.qa.tdl.mappers.ToDoListMapper;

@SpringBootTest
public class ToDoListServiceTestUnit {
	
	@Autowired
	private ToDoListService toDoListService;

	@MockBean
	private ToDoListRepository toDoListRepository;

	@MockBean
	private ToDoListMapper toDoListMapper;

	private List<ToDoList> toDoLists;
	private List<ToDoListDTO> toDoListDTOs;

	private ToDoList validToDoList;
	private ToDoListDTO validToDoListDTO;

	@BeforeEach
	public void init() {
		validToDoList = new ToDoList("test", true);
		validToDoListDTO = new ToDoListDTO("test", true);

		toDoLists = new ArrayList<ToDoList>();
		toDoListDTOs = new ArrayList<ToDoListDTO>();

		toDoLists.add(validToDoList);
		toDoListDTOs.add(validToDoListDTO);
	}
	
	@Test
	public void checkAllToDoListsTest() {
		when(toDoListRepository.findAll()).thenReturn(toDoLists);
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(toDoListDTOs).isEqualTo(toDoListService.checkAllToDoLists());

		verify(toDoListRepository, times(1)).findAll();
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void readByIdTest() {
		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validToDoList));
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(validToDoListDTO).isEqualTo(toDoListService.readById(validToDoList.getListId()));

		verify(toDoListRepository, times(1)).findById(Mockito.anyInt());
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void createToDoListTest() {
		when(toDoListRepository.save(Mockito.any(ToDoList.class))).thenReturn(validToDoList);
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(validToDoListDTO).isEqualTo(toDoListService.createToDoList(validToDoList));

		verify(toDoListRepository, times(1)).save(Mockito.any(ToDoList.class));
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void updateToDoListTest() {
		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validToDoList));
		when(toDoListRepository.save(Mockito.any(ToDoList.class))).thenReturn(validToDoList);
		when(toDoListMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validToDoListDTO);

		assertThat(validToDoListDTO).isEqualTo(toDoListService.updateToDoList(validToDoList.getListId(), validToDoList));

		verify(toDoListRepository, times(1)).findById(Mockito.anyInt());
		verify(toDoListRepository, times(1)).save(Mockito.any(ToDoList.class));
		verify(toDoListMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void deleteToDoListTest() {
		when(toDoListRepository.existsById(Mockito.anyInt())).thenReturn(true);

		assertThat(true).isEqualTo(toDoListService.deleteToDoList(validToDoList.getListId()));

		verify(toDoListRepository, times(1)).existsById(Mockito.anyInt());
		verify(toDoListRepository, times(1)).deleteById(Mockito.anyInt());
	}
	
	@Test
	public void ExceptionTest() {
		when(toDoListRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		when(toDoListRepository.existsById(Mockito.anyInt())).thenReturn(false);

		assertThrows(EntityNotFoundException.class, () -> toDoListService.readById(1));
		assertThrows(EntityNotFoundException.class, () -> toDoListService.deleteToDoList(1));
		assertThrows(EntityNotFoundException.class, () -> toDoListService.updateToDoList(1, validToDoList));

		verify(toDoListRepository, times(2)).findById(Mockito.anyInt());
		verify(toDoListRepository, times(1)).existsById(Mockito.anyInt());
	}

}
