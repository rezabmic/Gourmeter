package cz.cvut.fel.jee.gourmeter.dto;

import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

public class MarkerDTO {

	private final Long id;
	private final Long facilityId;
	private final Double latitude;
	private final Double longitude;
	private final String title;
	private final String description;
	private final String url;
	private final CategoryDTO category;
	private final List<TagDTO> tags;
	private final List<OpeningHoursDTO> openingHours;

	public MarkerDTO(CateringFacility f) {
		id = f.getId();
		facilityId = f.getId();
		latitude = f.getLatitude();
		longitude = f.getLongitude();
		title = f.getName();
		description = f.getDescription();
		url = f.getUrl();
		category = new CategoryDTO(f.getCategory());
		tags = DTOUtils.getFacilityTags(f);
		openingHours = DTOUtils.getHoursDTO(f);
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public List<OpeningHoursDTO> getOpeningHours() {
		return openingHours;
	}

	public Long getId() {
		return id;
	}

}
