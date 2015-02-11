package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.Category;

public class CategoryDTO {

	private Long id;
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Category c) {
		id = c.getId();
		name = c.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
