package com.qa.tdl.dto;

public class ItemDTO {

	private int itemId;

	private String textBody;	

	private Boolean taskDone;
	
	public ItemDTO() {
		super();
	}

	public ItemDTO(int itemId, String textBody, Boolean taskDone) {
		super();
		this.itemId = itemId;
		this.textBody = textBody;
		this.taskDone = taskDone;
	}
	
	public String getIdentification() {
		return itemId + " " + textBody + " " + taskDone;
	}
	
	public void setIdentification(String in) {
		String[] parts = in.split(" ");
		this.itemId = Integer.parseInt(parts[0]);
		this.textBody = parts[1];
		this.taskDone = Boolean.valueOf(parts[2]);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		result = prime * result + ((taskDone == null) ? 0 : taskDone.hashCode());
		result = prime * result + ((textBody == null) ? 0 : textBody.hashCode());
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
		ItemDTO other = (ItemDTO) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "ItemDTO [itemId=" + itemId + ", textBody=" + textBody + ", taskDone=" + taskDone + "]";
	}
	
}
