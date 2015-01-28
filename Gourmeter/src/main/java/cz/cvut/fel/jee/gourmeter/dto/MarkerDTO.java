package cz.cvut.fel.jee.gourmeter.dto;

import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

public class MarkerDTO {

	private final Long facilityId;
	private final Double latitude;
	private final Double longitude;
	private final MarkerFacilityDTO facility;

	public MarkerDTO(CateringFacility f) {
		facilityId = f.getId();
		latitude = f.getLatitude();
		longitude = f.getLongitude();
		facility = new MarkerFacilityDTO(f);
	}

	public static class MarkerFacilityDTO {

		private final String name;
		private final String type;
		private final List<TagDTO> tags;

		public MarkerFacilityDTO(CateringFacility f) {
			name = f.getName();
			type = f.getCategory().getName();
			tags = DTOUtils.getFacilityTags(f);
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public List<TagDTO> getTags() {
			return tags;
		}
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

	public MarkerFacilityDTO getFacility() {
		return facility;
	}

}
