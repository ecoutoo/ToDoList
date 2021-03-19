package com.qa.tdl.data.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "todolist")
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "list_id")
	private int listId;
	
	@Column(name = "title", unique = true)
	@NotNull
	private String title;
	
	@Column(name = "completed")
	@NotNull
	private Boolean completed;
	
	@OneToMany(mappedBy = "todolist", fetch = FetchType.LAZY, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Item> item;
	
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

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completed == null) ? 0 : completed.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		ToDoList other = (ToDoList) obj;
		if (completed == null) {
			if (other.completed != null)
				return false;
		} else if (!completed.equals(other.completed))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
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
		return "ToDoList [listId=" + listId + ", title=" + title + ", completed=" + completed + ", item=" + item + "]";
	}

}
