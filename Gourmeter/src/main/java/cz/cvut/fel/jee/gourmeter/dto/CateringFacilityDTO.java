package cz.cvut.fel.jee.gourmeter.dto;

import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

/** 
 * When user clicks to some marker rendered on the map, CateringFacilityDTO is queried. 
 * 
 * @author Michal
 *
 */
public class CateringFacilityDTO extends MarkerDTO{

	private final String title;
	private final String description;
	private final String url;
	private final List<TagWithReviewsDTO> tags;
	private final List<OpeningHoursDTO> openingHours;

	public CateringFacilityDTO(CateringFacility f) {
		super(f);
		title = f.getName();
		description = f.getDescription();
		url = f.getUrl();
		tags = DTOUtils.getFacilityTags(f);
		openingHours = DTOUtils.getHoursDTO(f);
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

	public List<TagWithReviewsDTO> getTags() {
		return tags;
	}

	public List<OpeningHoursDTO> getOpeningHours() {
		return openingHours;
	}
}
