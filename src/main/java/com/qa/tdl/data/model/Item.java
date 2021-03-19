package com.qa.tdl.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item")
public class Item {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int itemId;
	
	@Column(name = "textbody")
	@NotNull
	private String textBody;
	
	@NotNull
	private Boolean taskDone;
	
	@ManyToOne(targetEntity = ToDoList.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_list_id") 
	private ToDoList toDoList;
	
	public Item() {
		
	}
	
	public Item(String textBody, Boolean taskDone) {
		super();
		this.textBody = textBody;
		this.taskDone = taskDone;
	}
	
	public Item(int itemId, String textBody, Boolean taskDone) {
		super();
		this.itemId = itemId;
		this.textBody = textBody;
		this.taskDone = taskDone;
	}
	
	public Item(int itemId, String textBody, Boolean taskDone, ToDoList toDoList) {
		super();
		this.itemId = itemId;
		this.textBody = textBody;
		this.taskDone = taskDone;
		this.toDoList = toDoList;
	}


	public int getItemId() {
		return itemId;
	}

	public void setItemId(int id) {
		this.itemId = id;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public Boolean getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(Boolean taskDone) {
		this.taskDone = taskDone;
	}

	public ToDoList getToDoList() {
		return toDoList;
	}

	public void setToDoList(ToDoList toDoList) {
		this.toDoList = toDoList;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", textBody=" + textBody + ", taskDone=" + taskDone + ", toDoList=" + toDoList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		result = prime * result + ((taskDone == null) ? 0 : taskDone.hashCode());
		result = prime * result + ((textBody == null) ? 0 : textBody.hashCode());
		result = prime * result + ((toDoList == null) ? 0 : toDoList.hashCode());
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
		Item other = (Item) obj;
		if (itemId != other.itemId)
			return false;
		if (taskDone == null) {
			if (other.taskDone != null)
				return false;
		} else if (!taskDone.equals(other.taskDone))
			return false;
		if (textBody == null) {
			if (other.textBody != null)
				return false;
		} else if (!textBody.equals(other.textBody))
			return false;
		if (toDoList == null) {
			if (other.toDoList != null)
				return false;
		} else if (!toDoList.equals(other.toDoList))
			return false;
		return true;
	} 
	
}


