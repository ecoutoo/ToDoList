package com.qa.tdl.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.data.model.ToDoList;
import com.qa.tdl.dto.ToDoListDTO;
import com.qa.tdl.data.repository.ToDoListRepository;
import com.qa.tdl.mappers.ToDoListMapper;

@Service
public class ToDoListService {
	
	 private ToDoListRepository toDoListRepository;	 
	 private ToDoListMapper toDoListMapper;
	 
	 @Autowired
	 public ToDoListService(ToDoListRepository toDoListRepository, ToDoListMapper toDoListMapper) {
		 this.toDoListMapper = toDoListMapper;
		 this.toDoListRepository = toDoListRepository;
	 }
	 
	 //@Transactional
	 public List<ToDoListDTO> checkAllToDoLists(){
		 List<ToDoList> toDoListInDb= toDoListRepository.findAll();
		 //List<ToDoListDTO> toDoListDTO= new ArrayList<ToDoListDTO>();
		 List<ToDoListDTO> toDoListDTO= new ArrayList<>();		 
		 toDoListInDb.forEach(toDoList -> toDoListDTO.add(toDoListMapper.mapToDTO(toDoList)));
		 return toDoListDTO;
	 }
	 
	 public ToDoListDTO readById(Integer id) {
		 Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);
		 if (optionalToDoList.isPresent()) {
			 return toDoListMapper.mapToDTO(optionalToDoList.get());
		 }else {
			 throw new EntityNotFoundException();
		 }
	 }
	 
	 public ToDoListDTO createToDoList(ToDoList toDoList) {	 
		 return toDoListMapper.mapToDTO(toDoListRepository.save(toDoList));
	 }
	 
	 public boolean deleteToDoList(Integer id) {
		 if(!toDoListRepository.existsById(id)) {
			 throw new EntityNotFoundException();
		 }
		 toDoListRepository.deleteById(id);
		 return true;
		 
	 }
	 
	 public ToDoListDTO updateToDoList(Integer id, ToDoList toDoList) {
		 Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);
		 ToDoList toDoListInDb;
		 if (optionalToDoList.isPresent()) {
			 toDoListInDb = optionalToDoList.get();
		 }else {
			 throw new EntityNotFoundException();
		 }
		 toDoListInDb.setTitle(toDoList.getTitle());
		 toDoListInDb.setCompleted(toDoList.getCompleted());
		 toDoListRepository.save(toDoListInDb);
		 return toDoListMapper.mapToDTO(toDoListInDb);
	 }

}
