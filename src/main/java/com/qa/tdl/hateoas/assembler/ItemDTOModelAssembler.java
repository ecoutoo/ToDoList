package com.qa.tdl.hateoas.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.qa.tdl.controller.ItemController;
import com.qa.tdl.data.model.Item;
import com.qa.tdl.dto.ItemDTO;
import com.qa.tdl.mappers.ItemMapper;

@Component
public class ItemDTOModelAssembler implements RepresentationModelAssembler<ItemDTO, EntityModel<ItemDTO>> {
	
	private ItemMapper itemMapper;
	
	@Autowired
	public ItemDTOModelAssembler(ItemMapper itemMapper) {
		super();
		this.itemMapper = itemMapper;
	}
	
	@Override
	public EntityModel<ItemDTO> toModel(ItemDTO entity) {
		EntityModel<ItemDTO> entityModel = EntityModel.of(entity,
				linkTo(methodOn(ItemController.class).getItemById(entity.getItemId())).withSelfRel().withTitle("GET"),
				linkTo(methodOn(ItemController.class).getAllItems()).withRel("ducks").withTitle("GET")
		);

		
		return entityModel;
	}

}
