package com.qa.tdl.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.data.model.Item;
import com.qa.tdl.data.repository.ItemRepository;
import com.qa.tdl.dto.ItemDTO;
import com.qa.tdl.mappers.ItemMapper;

@SpringBootTest
public class ItemServiceIntegrationTest {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemMapper itemMapper;
	
	private List<Item> items;
	private List<ItemDTO> itemDTOs;
	
	private Item validItem;
	private ItemDTO validItemDTO;
	
	@BeforeEach
	public void init() {
		validItem = new Item("TestItem", true);
		
		items = new ArrayList<Item>();
		itemDTOs = new ArrayList<ItemDTO>();
				
		itemRepository.deleteAll();
		
		validItem = itemRepository.save(validItem);
		
		validItemDTO = itemMapper.mapToDTO(validItem);
		
		items.add(validItem);
		itemDTOs.add(validItemDTO);
	}
	
	@Test
	public void readAllItemsTest() {
		List<ItemDTO> itemsInDb = itemService.readAllItems();	
		assertThat(itemDTOs).isEqualTo(itemsInDb);
	}
	
	@Test
	public void readbyIdTest() {
		ItemDTO itemsInDb = itemService.readById(validItem.getItemId());
		assertThat(validItemDTO).isEqualTo(itemsInDb);
	}
	
	@Test
	public void createItemsTest() {
		Item newItem = new Item("TestItem2", true);
		ItemDTO itemsInDb = itemService.createItem(newItem);		
		assertThat(itemMapper.mapToDTO(newItem)).isEqualTo(itemsInDb);
	}
	
	@Test
	public void updateItemTest() {
		Item changedItem = new Item(6, "TestItem3", true);
		ItemDTO itemsInDb = itemService.updateItem(validItem.getItemId(), changedItem);		
		assertThat(itemMapper.mapToDTO(changedItem)).isEqualTo(itemsInDb);
	}

}
