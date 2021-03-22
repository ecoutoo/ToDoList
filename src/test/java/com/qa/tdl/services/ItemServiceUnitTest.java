package com.qa.tdl.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.data.model.Item;
import com.qa.tdl.data.repository.ItemRepository;
import com.qa.tdl.dto.ItemDTO;
import com.qa.tdl.mappers.ItemMapper;

@SpringBootTest
public class ItemServiceUnitTest {
	
	@Autowired
	private ItemService itemService;

	@MockBean
	private ItemRepository itemRepository;

	@MockBean
	private ItemMapper itemMapper;

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
	public void readAllItemsTest() {
		when(itemRepository.findAll()).thenReturn(items);
		when(itemMapper.mapToDTO(Mockito.any(Item.class))).thenReturn(validItemDTO);

		assertThat(itemDTOs).isEqualTo(itemService.readAllItems());

		verify(itemRepository, times(1)).findAll();
		verify(itemMapper, times(1)).mapToDTO(Mockito.any(Item.class));
	}
	
	@Test
	public void readByIdTest() {
		when(itemRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validItem));
		when(itemMapper.mapToDTO(Mockito.any(Item.class))).thenReturn(validItemDTO);

		assertThat(validItemDTO).isEqualTo(itemService.readById(validItem.getItemId()));

		verify(itemRepository, times(1)).findById(Mockito.anyInt());
		verify(itemMapper, times(1)).mapToDTO(Mockito.any(Item.class));
	}
	
	@Test
	public void createItemTest() {
		when(itemRepository.save(Mockito.any(Item.class))).thenReturn(validItem);
		when(itemMapper.mapToDTO(Mockito.any(Item.class))).thenReturn(validItemDTO);

		assertThat(validItemDTO).isEqualTo(itemService.createItem(validItem));

		verify(itemRepository, times(1)).save(Mockito.any(Item.class));
		verify(itemMapper, times(1)).mapToDTO(Mockito.any(Item.class));
	}
	
	@Test
	public void updateItemTest() {
		when(itemRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(validItem));
		when(itemRepository.save(Mockito.any(Item.class))).thenReturn(validItem);
		when(itemMapper.mapToDTO(Mockito.any(Item.class))).thenReturn(validItemDTO);

		assertThat(validItemDTO).isEqualTo(itemService.updateItem(validItem.getItemId(), validItem));

		verify(itemRepository, times(1)).findById(Mockito.anyInt());
		verify(itemRepository, times(1)).save(Mockito.any(Item.class));
		verify(itemMapper, times(1)).mapToDTO(Mockito.any(Item.class));
	}
	
	@Test
	public void deleteItemTest() {
		when(itemRepository.existsById(Mockito.anyInt())).thenReturn(true);

		assertThat(true).isEqualTo(itemService.deleteItem(validItem.getItemId()));

		verify(itemRepository, times(1)).existsById(Mockito.anyInt());
		verify(itemRepository, times(1)).deleteById(Mockito.anyInt());
	}
	
	@Test
	public void ExceptionTest() {
		when(itemRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		when(itemRepository.existsById(Mockito.anyInt())).thenReturn(false);

		assertThrows(EntityNotFoundException.class, () -> itemService.readById(1));
		assertThrows(EntityNotFoundException.class, () -> itemService.deleteItem(1));
		assertThrows(EntityNotFoundException.class, () -> itemService.updateItem(1, validItem));

		verify(itemRepository, times(2)).findById(Mockito.anyInt());
		verify(itemRepository, times(1)).existsById(Mockito.anyInt());
	}


}
