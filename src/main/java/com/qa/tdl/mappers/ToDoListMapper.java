package com.qa.tdl.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.tdl.data.model.ToDoList;

@Component
public class ToDoListMapper {
	
	private ModelMapper modelMapper;
	
	@Autowired
	public ToDoListMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}
