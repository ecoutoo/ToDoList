package com.qa.tdl.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdl.data.model.Item;
import com.qa.tdl.dto.ItemDTO;

@SpringBootTest
public class ItemMapperTest {

	@Autowired
	private ItemMapper itemMapper;
	
	private Item item;
	private ItemDTO itemDTO;
	
	@BeforeEach
	public void init() {
		item = new Item("test", true);
		itemDTO = new ItemDTO("test", true);
	}
	
	@Test
	public void mapToDTOTest() {
		assertThat(itemDTO).isEqualTo(itemMapper.mapToDTO(item));
	}
	
	@Test
	public void mapToTaskTest() {
		assertThat(item).isEqualTo(itemMapper.mapToItem(itemDTO));
	}
}
