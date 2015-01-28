package cz.cvut.fel.jee.gourmeter.dto;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.OpeningHours;

public class CateringFacilityDTO {

	private final String title;
	private final String url;
	private final String description;
	private final Double latitude;
	private final Double longitude;

	private final MenuDTO menu;
	private final AddressDTO address;
	private final List<TagDTO> tags;
	private final List<OpeningHoursDTO> openingHours;

	public CateringFacilityDTO(CateringFacility f) {
		title = f.getName();
		url = f.getUrl();
		description = f.getDescription();
		latitude = f.getLatitude();
		longitude = f.getLongitude();
		menu = new MenuDTO(f.getMenuFrom(), f.getMenuTo());
		address = new AddressDTO(f.getCity(), f.getStreet(), f.getHouseNumber());

		tags = DTOUtils.getFacilityTags(f);
		openingHours = new ArrayList<>();
		for (OpeningHours oh : f.getOpeningHours()) {
			openingHours.add(new OpeningHoursDTO(oh));
		}
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public MenuDTO getMenu() {
		return menu;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public List<OpeningHoursDTO> getOpeningHours() {
		return openingHours;
	}
	
	
}
