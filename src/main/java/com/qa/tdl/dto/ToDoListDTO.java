package com.qa.tdl.dto;

public class ToDoListDTO {
	
	private int listId;
	
	private String title;
	
	private Boolean completed;
	
	@Override
	public String toString() {
		return "ToDoListDTO [id=" + listId + ", name=" + title + ", status=" + completed + "]";
	}
	
	public ToDoListDTO() {
		
	}
	
	public ToDoListDTO(int listId, String title, Boolean completed) {
		super();
		this.listId = listId;
		this.title = title;
		this.completed = completed;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completed == null) ? 0 : completed.hashCode());
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
		if (listId != other.listId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
