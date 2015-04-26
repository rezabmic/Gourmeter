package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class TagDTO {

	private long id;
	private String name;

	public TagDTO() {
	}
	
	public TagDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TagDTO(Tag bo) {
		this.id = bo.getId();
		this.name = bo.getName();
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
