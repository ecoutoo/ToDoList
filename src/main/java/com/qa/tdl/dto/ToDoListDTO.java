package com.qa.tdl.dto;

import java.util.List;

public class ToDoListDTO {
	
	private int listId;
	
	private String title;
	
	private Boolean completed;
	
	private List<ItemDTO> items;
	
	public ToDoListDTO() {
		
	}
	
	public ToDoListDTO(int listId, String title, Boolean completed, List<ItemDTO> items) {
		super();
		this.listId = listId;
		this.title = title;
		this.completed = completed;
		this.items = items;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completed == null) ? 0 : completed.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + listId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoListDTO other = (ToDoListDTO) obj;
		if (completed == null) {
			if (other.completed != null)
				return false;
		} else if (!completed.equals(other.completed))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (listId != other.listId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ToDoListDTO [listId=" + listId + ", title=" + title + ", completed=" + completed + ", items=" + items
				+ "]";
	}
	
}
