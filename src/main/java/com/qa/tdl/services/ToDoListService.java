package com.qa.tdl.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.data.model.ToDoList;
import com.qa.tdl.data.repository.ToDoListRepo;
import com.qa.tdl.mappers.ToDoListMapper;

@Service
public class ToDoListService {
	
	 private ToDoListRepo toDoListRepository;	 
	 private ToDoListMapper toDoListMapper;
	 
	 @Autowired
	 public ToDoListService(ToDoListRepo toDoListRepository, ToDoListMapper toDoListMapper) {
		 this.toDoListMapper = toDoListMapper;
		 this.toDoListRepository = toDoListRepository;
	 }
	 public List<ToDoList> checkAllToDoLists(){
		 List<ToDoList> toDoList= toDoListRepository.findAll();
		 //List<ToDoList> toDoListDTOs= new ArrayList<ToDoList>();
		 
		 //toDoLists.forEach(toDoList -> toDoListDTOs.add(toDoListMapper.mapToDTO(toDoList)));
		 return toDoList;
	 }
	 
	 public ToDoList readById(Integer id) {
		 Optional<ToDoList> toDoList = toDoListRepository.findById(id);
		 if (toDoList.isPresent()) {
			 return toDoList.get();
		 }else {
			 throw new EntityNotFoundException();
		 }
	 }
	 
	 public ToDoList createToDoList(ToDoList toDoList) {
		 return toDoListRepository.save(toDoList);
	 }
	 
	 public boolean deleteToDoList(Integer id) {
		 if(!toDoListRepository.existsById(id)) {
			 throw new EntityNotFoundException();
		 }
		 toDoListRepository.deleteById(id);
		 
		 boolean exists = toDoListRepository.existsById(id);
		 
		 return !exists;
	 }
	 
	 public ToDoList updateToDoList(Integer id, ToDoList toDoList) {
		 Optional<ToDoList> otoDoList = toDoListRepository.findById(id);
		 ToDoList toDoList2;
		 if (otoDoList.isPresent()) {
			 toDoList2 = otoDoList.get();
		 }else {
			 throw new EntityNotFoundException();
		 }
		 toDoList2.setTitle(toDoList.getTitle());
		 toDoList2.setCompleted(toDoList.getCompleted());
		 toDoListRepository.save(toDoList2);
		 return toDoList2;
	 }

}
