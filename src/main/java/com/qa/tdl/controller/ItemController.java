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
	
	private ItemDTOModelAssembler itemDTOModelAssembler;
	
	@Autowired 
	public ItemController(ItemService itemService, ItemDTOModelAssembler itemDTOModelAssembler) {
		this.itemService = itemService;
		this.itemDTOModelAssembler = itemDTOModelAssembler;
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<ItemDTO>>> getAllItems() {

		List<ItemDTO> data = itemService.readAllItems();
		
		List<EntityModel<ItemDTO>> entityModels = data.stream().map(item -> 
			itemDTOModelAssembler.toModel(item)
		).collect(Collectors.toList());
		
		CollectionModel<EntityModel<ItemDTO>> collectionModel = CollectionModel.of(
				entityModels,
				linkTo(methodOn(ItemController.class).getAllItems()).withSelfRel()
		);
		
		return new ResponseEntity<CollectionModel<EntityModel<ItemDTO>>>(collectionModel, HttpStatus.OK);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<EntityModel<ItemDTO>> getItemById(@PathVariable("id") Integer id) {

		ItemDTO item = itemService.readById(id);
		
		EntityModel<ItemDTO> itemEntityModel = EntityModel.of(item,
				linkTo(methodOn(ItemController.class).getItemById(id)).withSelfRel(), 
				linkTo(methodOn(ItemController.class).getAllItems()).withRel("items")); 
		
		// Return the item data in a response
		return new ResponseEntity<EntityModel<ItemDTO>>(itemEntityModel, HttpStatus.OK);
	}
	
	@GetMapping("/alt")
	public ResponseEntity<ItemDTO> getItemByIdAlt(@RequestParam("id") Integer id) {
		
		
		ItemDTO item = itemService.readById(id);
		
		return new ResponseEntity<ItemDTO>(item, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createItem(@Valid @RequestBody Item item) {

		
		ItemDTO newItem = itemService.createItem(item);
		
		// Wrapping the DTO with the entity model assembler
		EntityModel<ItemDTO> model = itemDTOModelAssembler.toModel(newItem);

		ResponseEntity<?> response = ResponseEntity.created(
				model.getRequiredLink(IanaLinkRelations.SELF).toUri()
		).body(model);
		

		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateItem(@PathVariable("id") Integer id,
										   @RequestBody Item item) {
		
		ItemDTO updatedItem = itemService.updateItem(id, item);
		
		EntityModel<ItemDTO> model = itemDTOModelAssembler.toModel(updatedItem);
		
		HttpHeaders headers = new HttpHeaders();

		headers.add("Location", model.getRequiredLink(IanaLinkRelations.SELF).toUri().toString());
		
		return new ResponseEntity<EntityModel<ItemDTO>>(model, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {		
		return new ResponseEntity<Boolean>(itemService.deleteItem(id), HttpStatus.NO_CONTENT);
	}

}
