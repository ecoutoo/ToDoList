package com.qa.tdl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.services.ToDoListService;
import com.qa.tdl.data.model.ToDoList;
import com.qa.tdl.dto.ToDoListDTO;

@CrossOrigin
@RestController
@RequestMapping(path = "/todolist") 
public class ToDoListController {
	
	private ToDoListService toDoListService;
	
	@Autowired // constructor injection (injected from the application context)
	public ToDoListController(ToDoListService toDoListService) throws Exception {
		this.toDoListService = toDoListService;
	}
	
	// localhost:8080/todolist
	@GetMapping
	public ResponseEntity<List<ToDoListDTO>> getAllToDoList() {
		
		List<ToDoListDTO> data = toDoListService.checkAllToDoLists();
		
		return new ResponseEntity<List<ToDoListDTO>>(data, HttpStatus.OK);
	}
	
	// localhost:8080/todolist/3
	@GetMapping("/{id}") 
	public ResponseEntity<ToDoListDTO> getToDoListById(@PathVariable("id") int id) {
		
		ToDoListDTO toDoList = toDoListService.readById(id);
		
		return new ResponseEntity<ToDoListDTO>(toDoList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ToDoListDTO> createToDoList(@Valid @RequestBody ToDoList toDoList) {
		
		ToDoListDTO newToDoList = toDoListService.createToDoList(toDoList);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newToDoList.getListId()));
	
		return new ResponseEntity<ToDoListDTO>(newToDoList, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ToDoListDTO> updateToDoList(@PathVariable("id") int id,
										   @RequestBody ToDoList toDoList) {
		ToDoListDTO updatedToDoList = toDoListService.updateToDoList(id, toDoList);
		
		return new ResponseEntity<ToDoListDTO>(updatedToDoList, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteToDoList(@PathVariable("id") int id) {		
		return new ResponseEntity<Boolean>(toDoListService.deleteToDoList(id), HttpStatus.OK);
	}
}
