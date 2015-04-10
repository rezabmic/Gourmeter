package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

/**
 * MarkerDTO uses only few attributes of CateringFacility BO needed to render markers on map. 
 * Other attributes of CateringFacility are not queried (lazy fetching).
 * 
 * @author Michal
 *
 */
public class MarkerDTO {	

	private final Long id;
	private final Long facilityId;
	private final Double latitude;
	private final Double longitude;
	
	public MarkerDTO(CateringFacility f) {
		id = f.getId();
		facilityId = f.getId();
		latitude = f.getLatitude();
		longitude = f.getLongitude();
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

	public Long getId() {
		return id;
	}

}
