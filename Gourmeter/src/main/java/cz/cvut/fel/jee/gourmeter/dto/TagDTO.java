package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class TagDTO {

	private String name;
	private Integer recommended;
	private Integer reviewed;

	public TagDTO() {
	}

	public TagDTO(Tag bo, Integer recommended, Integer reviewed) {
		this.name = bo.getName();
		this.recommended = recommended;
		this.reviewed = reviewed;
	}

	public TagDTO(String name, Integer recommended, Integer reviewed) {
		this.name = name;
		this.recommended = recommended;
		this.reviewed = reviewed;
	}
	
	public TagDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getRecommended() {
		return recommended;
	}

	public Integer getReviewed() {
		return reviewed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	public void setReviewed(Integer reviewed) {
		this.reviewed = reviewed;
	}

}
