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

@CrossOrigin
@RestController
@RequestMapping(path = "/todolist") // This controller has a base path of /todolist (localhost:8080/todolist)
public class ToDoListController {
	
	//localhost:8080/
	//@RequestMapping(method = RequestMethod.GET)
	
	//@Autowired //field injection
	private ToDoListService toDoListService;
	
	@Autowired // constructor injection (injected from the application context)
	public ToDoListController(ToDoListService toDoListService) {
		this.toDoListService = toDoListService;
	}
	
	// localhost:8080/todolist
	@GetMapping
	public ResponseEntity<List<ToDoList>> getAllToDoList() {
		
		// Response has headers, a body and a status code
		HttpHeaders httpHeaders = new HttpHeaders(); // Creating some headers
		httpHeaders.add("Location", "1442"); // Adding a header
		
		// Requesting our toDoListDTO data from the toDoListService
		List<ToDoList> data = toDoListService.checkAllToDoLists();
		
		// returning a response of type ResponseEntity(Body, Headers, HttpStatus)
		return new ResponseEntity<List<ToDoList>>(data, httpHeaders, HttpStatus.OK);
	}
	
	// localhost:8080/todolist/3
	@GetMapping("/{id}") // {id} is a path variable
	public ResponseEntity<ToDoList> getToDoListById(@PathVariable("id") int id) {
		// The path variable is captured by the @PathVariable annotation
		// - WE MUST SUPPLY A MATCHING NAME WITHIN THE PARENTHESIS
		// - WE MUST SUPPLY AN APPROPRIATE DATA TYPE FOR THE VAR TO CONVERT TO
		
		// Get our toDoList data using the service
		ToDoList toDoList = toDoListService.readById(id);
		
		// Return the toDoList data in a response
		return new ResponseEntity<ToDoList>(toDoList, HttpStatus.OK);
	}
	
	// Spring Boots version of @PathParam is @RequestParam(defaultValue = "")
	// localhost:8080/todolist/alt?id=1
	@GetMapping("/alt")
	public ResponseEntity<ToDoList> getToDoListByIdAlt(@RequestParam("id") int id) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		ToDoList toDoList = toDoListService.readById(id);
		
		return new ResponseEntity<ToDoList>(toDoList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ToDoList> createToDoList(@Valid @RequestBody ToDoList toDoList) {
		// A toDoList is retrieved from the incoming request body (the conversion from json to toDoList is automatic)
		// - `@RequestBody toDoList toDoList` makes this happen
		// - @Valid is used to employ our models validation on the incoming request
		
		ToDoList newToDoList = toDoListService.createToDoList(toDoList);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newToDoList.getListId()));
	
		return new ResponseEntity<ToDoList>(newToDoList, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ToDoList> updateToDoList(@PathVariable("id") int id,
										   @RequestBody ToDoList toDoList) {
		ToDoList updatedToDoList = toDoListService.updateToDoList(id, toDoList);
		
		return new ResponseEntity<ToDoList>(updatedToDoList, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteToDoList(@PathVariable("id") int id) {		
		return new ResponseEntity<Boolean>(toDoListService.deleteToDoList(id), HttpStatus.OK);
	}
	
	// @GetMapping (retrieving something)
	// @PostMapping (creating something)
	// @PutMapping (generalised update)
	// @PatchMapping (specific update)
	// @DeleteMapping (deleting something)

}
