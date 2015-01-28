package cz.cvut.fel.jee.gourmeter.dto;

public class TagDTO {

	private final String name;
	private final Boolean recommended;
	private final Boolean reviewed;

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

}
