package com.qa.tdl.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.qa.tdl.data.model.Item;
import com.qa.tdl.data.repository.ItemRepository;
import com.qa.tdl.dto.ItemDTO;
import com.qa.tdl.mappers.ItemMapper;
import com.qa.tdl.services.ItemService;
import com.qa.tdl.hateoas.assembler.ItemDTOModelAssembler;

@RestController
@RequestMapping(path = "/item") 
@CrossOrigin
public class ItemController {
	
	private ItemService itemService;
	
	@Autowired 
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getAllItems() {
		List<ItemDTO> data = itemService.readAllItems();		
		return new ResponseEntity<List<ItemDTO>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<ItemDTO> getItemById(@PathVariable("id") Integer id) {
		ItemDTO item = itemService.readById(id);		
		return new ResponseEntity<ItemDTO>(item, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody Item item) {	
		ItemDTO newItem = itemService.createItem(item);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newItem.getItemId()));
		
		return new ResponseEntity<ItemDTO>(newItem, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemDTO> updateItem(@PathVariable("id") Integer id, @RequestBody Item item) {
		ItemDTO updatedItem = itemService.updateItem(id, item);
		return new ResponseEntity<ItemDTO>(updatedItem, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {		
		return new ResponseEntity<Boolean>(itemService.deleteItem(id), HttpStatus.NO_CONTENT);
	}

}
