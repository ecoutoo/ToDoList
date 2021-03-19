package com.qa.tdl.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.data.model.Item;
import com.qa.tdl.data.repository.ItemRepository;
import com.qa.tdl.dto.ItemDTO;
import com.qa.tdl.mappers.ItemMapper;

@Service
public class ItemService {

		private ItemRepository itemRepository;
		
		private ItemMapper itemMapper;
		
		@Autowired
		public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
			this.itemRepository = itemRepository;
			this.itemMapper = itemMapper;
		}

		public List<ItemDTO> readAllItems() {
			List<Item> items = itemRepository.findAll();
			List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
			
			items.forEach(item -> itemDTOs.add(itemMapper.mapToDTO(item)));

			return itemDTOs;
		}
		
		public ItemDTO readById(Integer id) {
			Optional<Item> item = itemRepository.findById(id);
			
			if (item.isPresent()) {
				return itemMapper.mapToDTO(item.get());
			} else {
				throw new EntityNotFoundException();
			}
		}
		
		public ItemDTO createItem(Item item) {
			Item newItem = itemRepository.save(item);
			
			return itemMapper.mapToDTO(newItem);
		}
		
		public ItemDTO updateItem(Integer id, Item item) throws EntityNotFoundException {
			Optional<Item> itemInDbOpt = itemRepository.findById(id);
			Item itemInDb;
			
			if (itemInDbOpt.isPresent()) {
				itemInDb = itemInDbOpt.get();
			} else {
				throw new EntityNotFoundException();
			}
			
			itemInDb.setTextBody(item.getTextBody());
			itemInDb.setTaskDone(item.getTaskDone());
			
			Item updatedItem = itemRepository.save(itemInDb);
			
			return itemMapper.mapToDTO(updatedItem);
		}
		
		public boolean deleteItem(Integer id) {
			if (!itemRepository.existsById(id)) {
				throw new EntityNotFoundException();
			}
			itemRepository.deleteById(id);
			
			boolean exists = itemRepository.existsById(id);
			
			return !exists;
		}
	
}
