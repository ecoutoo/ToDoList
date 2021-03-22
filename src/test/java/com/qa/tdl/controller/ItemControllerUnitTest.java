package com.qa.tdl.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.tdl.services.ItemService;
import com.qa.tdl.data.model.Item;
import com.qa.tdl.dto.ItemDTO;

@SpringBootTest
public class ItemControllerUnitTest {
	
	@Autowired
	private ItemController itemController;
	
	@MockBean
	private ItemService itemService;
	
	private List<Item> items;
	private List<ItemDTO> itemDTOs;
	
	private Item validItem;
	private ItemDTO validItemDTO;

	@BeforeEach
	public void init() {
		validItem = new Item("test", true);
		validItemDTO = new ItemDTO("test", true);
		
		items = new ArrayList<Item>();
		itemDTOs = new ArrayList<ItemDTO>();
		
		items.add(validItem);
		itemDTOs.add(validItemDTO);
	}
	
	@Test
	public void getAllItemsTest() {
		when(itemService.readAllItems()).thenReturn(itemDTOs);
		
		ResponseEntity<List<ItemDTO>> response = new ResponseEntity<List<ItemDTO>>(itemDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(itemController.getAllItems());
		
		verify(itemService, times(1)).readAllItems();
	}
	
	@Test
	public void getItemByIdTest() {
		when(itemService.readById(Mockito.anyInt())).thenReturn(validItemDTO);
		
		ResponseEntity<ItemDTO> response = new ResponseEntity<ItemDTO>(validItemDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(itemController.getItemById(Mockito.anyInt()));
		
		verify(itemService, times(1)).readById(Mockito.anyInt());
	}
	
	@Test
	public void createItemTest() {
		when(itemService.createItem(Mockito.any(Item.class))).thenReturn(validItemDTO);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Location", String.valueOf(validItemDTO.getItemId()));
		
		ResponseEntity<ItemDTO> response = new ResponseEntity<ItemDTO>(validItemDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(itemController.createItem(validItem));
		
		verify(itemService, times(1)).createItem(Mockito.any(Item.class));
	}
	
	@Test
	public void updateItemTest() {
		when(itemService.updateItem(Mockito.anyInt(), Mockito.any(Item.class))).thenReturn(validItemDTO);
		
		ResponseEntity<ItemDTO> response = new ResponseEntity<ItemDTO>(validItemDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(itemController.updateItem(validItem.getItemId(), validItem));
		
		verify(itemService, times(1)).updateItem(Mockito.anyInt(), Mockito.any(Item.class));
	}
	
	@Test
	public void deleteItemTest() {
		when(itemService.deleteItem(Mockito.anyInt())).thenReturn(true);
		
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		assertThat(response).isEqualTo(itemController.deleteItem(validItem.getItemId()));
		
		verify(itemService, times(1)).deleteItem(Mockito.anyInt());
	}

}
