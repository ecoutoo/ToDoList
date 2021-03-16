package com.qa.tdl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.duckdemo.Services.VibeService;
import com.example.duckdemo.data.model.Vibe;
import com.example.duckdemo.dto.VibeDTO;

@RestController
@RequestMapping(path = "/todolist") // This controller has a base path of /todolist (localhost:8080/todolist)
public class ToDoListController {
	
	//localhost:8080/
	//@RequestMapping(method = RequestMethod.GET)
	
	//@Autowired //field injection
	private VibeService vibeService;
	
	@Autowired // constructor injection (injected from the application context)
	public VibeController(VibeService vibeService) {
		this.vibeService = vibeService;
	}
	
	// localhost:8080/vibe
	@GetMapping
	public ResponseEntity<List<VibeDTO>> getAllVibe() {
		
		// Response has headers, a body and a status code
		HttpHeaders httpHeaders = new HttpHeaders(); // Creating some headers
		httpHeaders.add("Location", "1442"); // Adding a header
		
		// Requesting our vibeDTO data from the vibeService
		List<VibeDTO> data = vibeService.checkAllVibes();
		
		// returning a response of type ResponseEntity(Body, Headers, HttpStatus)
		return new ResponseEntity<List<VibeDTO>>(data, httpHeaders, HttpStatus.OK);
	}
	
	// localhost:8080/vibe/3
	@GetMapping("/{id}") // {id} is a path variable
	public ResponseEntity<VibeDTO> getVibeById(@PathVariable("id") int id) {
		// The path variable is captured by the @PathVariable annotation
		// - WE MUST SUPPLY A MATCHING NAME WITHIN THE PARENTHESIS
		// - WE MUST SUPPLY AN APPROPRIATE DATA TYPE FOR THE VAR TO CONVERT TO
		
		// Get our vibe data using the service
		VibeDTO vibe = vibeService.readById(id);
		
		// Return the vibe data in a response
		return new ResponseEntity<VibeDTO>(vibe, HttpStatus.OK);
	}
	
	// Spring Boots version of @PathParam is @RequestParam(defaultValue = "")
	// localhost:8080/vibe/alt?id=1
	@GetMapping("/alt")
	public ResponseEntity<VibeDTO> getVibeByIdAlt(@RequestParam("id") int id) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		VibeDTO vibe = vibeService.readById(id);
		
		return new ResponseEntity<VibeDTO>(vibe, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<VibeDTO> createVibe(@Valid @RequestBody Vibe vibe) {
		// A vibe is retrieved from the incoming request body (the conversion from json to vibe is automatic)
		// - `@RequestBody vibe vibe` makes this happen
		// - @Valid is used to employ our models validation on the incoming request
		
		VibeDTO newvibe = vibeService.createVibe(vibe);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newvibe.getId()));
	
		return new ResponseEntity<VibeDTO>(newvibe, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VibeDTO> updateVibe(@PathVariable("id") int id,
										   @RequestBody Vibe vibe) {
		VibeDTO updatedvibe = vibeService.updateVibe(id, vibe);
		
		return new ResponseEntity<VibeDTO>(updatedvibe, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteVibe(@PathVariable("id") int id) {		
		return new ResponseEntity<Boolean>(vibeService.deleteVibe(id), HttpStatus.OK);
	}
	
	// @GetMapping (retrieving something)
	// @PostMapping (creating something)
	// @PutMapping (generalised update)
	// @PatchMapping (specific update)
	// @DeleteMapping (deleting something)

}
