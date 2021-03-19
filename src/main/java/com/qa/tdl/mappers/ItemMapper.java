package com.qa.tdl.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.tdl.data.model.Item;
import com.qa.tdl.dto.ItemDTO;

@Component
public class ItemMapper {

	private ModelMapper modelMapper;

	@Autowired
	public ItemMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ItemDTO mapToDTO(Item toDoList) {
		return this.modelMapper.map(toDoList, ItemDTO.class);
	}

	public Item mapToItem(ItemDTO toDoListDTO) {
		return this.modelMapper.map(toDoListDTO, Item.class);
	}

}