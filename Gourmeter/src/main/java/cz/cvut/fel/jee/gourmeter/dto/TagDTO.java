package cz.cvut.fel.jee.gourmeter.dto;

public class TagDTO {

	private String name;
	private Boolean recommended;
	private Boolean reviewed;

	public TagDTO() {
	}

	public TagDTO(String name, Boolean recommended, Boolean reviewed) {
		this.name = name;
		this.recommended = recommended;
		this.reviewed = reviewed;
	}

	public String getName() {
		return name;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public Boolean getReviewed() {
		return reviewed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public void setReviewed(Boolean reviewed) {
		this.reviewed = reviewed;
	}

}
