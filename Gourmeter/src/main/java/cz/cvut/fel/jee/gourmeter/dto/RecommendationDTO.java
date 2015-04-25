package cz.cvut.fel.jee.gourmeter.dto;


public class RecommendationDTO {

	
	private boolean recommended;
	private long facilityId;
	private long tagId;
	private long userId;

	public RecommendationDTO() {
	}

	public RecommendationDTO(boolean recommended, long facilityId, long tagId,
			long userId) {
		this.recommended = recommended;
		this.facilityId = facilityId;
		this.tagId = tagId;
		this.userId = userId;
	}



	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}

	public long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
