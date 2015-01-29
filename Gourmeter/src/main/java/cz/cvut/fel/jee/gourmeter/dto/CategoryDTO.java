package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.Category;

public class CategoryDTO {

	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Category c) {
		name = c.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
