package com.qa.tdl.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listId;
	
	@Column(name = "name", unique = true)
	@NotNull
	private String title;
	
	@NotNull
	private Boolean completed;
	
	public ToDoList() {
		super();
	}

	public ToDoList(String title, Boolean completed) {
		super();
		this.title = title;
		this.completed = completed;
	}
	public ToDoList(int id, String title, Boolean completed) {
		super();
		this.listId = id;
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
		result = prime * result + listId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + completed.hashCode();
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
		ToDoList other = (ToDoList) obj;
		if (listId != other.listId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (completed != other.completed)
			return false;
		return true;
	}

}
