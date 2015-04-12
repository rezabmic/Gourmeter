package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.Tag;

public class TagWithReviewsDTO extends TagDTO{

	private Integer recommended;
	private Integer reviewed;

	public TagWithReviewsDTO() {
	}

	public TagWithReviewsDTO(Tag bo, Integer recommended, Integer reviewed) {
		super(bo);
		this.recommended = recommended;
		this.reviewed = reviewed;
	}

	public TagWithReviewsDTO(long id, String name, Integer recommended, Integer reviewed) {
		super(id,name);
		this.recommended = recommended;
		this.reviewed = reviewed;
	}

	public Integer getRecommended() {
		return recommended;
	}

	public Integer getReviewed() {
		return reviewed;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	public void setReviewed(Integer reviewed) {
		this.reviewed = reviewed;
	}

}
