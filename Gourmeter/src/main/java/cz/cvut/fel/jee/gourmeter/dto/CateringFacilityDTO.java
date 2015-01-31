package cz.cvut.fel.jee.gourmeter.dto;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;
import cz.cvut.fel.jee.gourmeter.bo.OpeningHours;

public class CateringFacilityDTO {

	private Long id;
	private String title;
	private String url;
	private String description;
	private Double latitude;
	private Double longitude;
	private Long categoryId;
	private MenuDTO menu;
	private AddressDTO address;
	private List<TagDTO> tags;
	private List<OpeningHoursDTO> openingHours;

	public CateringFacilityDTO() {
	}

	public CateringFacilityDTO(CateringFacility f) {
		title = f.getName();
		url = f.getUrl();
		description = f.getDescription();
		latitude = f.getLatitude();
		longitude = f.getLongitude();
		menu = new MenuDTO(f.getMenuFrom(), f.getMenuTo());
		address = new AddressDTO(f.getCity(), f.getStreet(), f.getHouseNumber());
		categoryId = f.getCategory().getId();
		tags = DTOUtils.getFacilityTags(f);
		openingHours = new ArrayList<>();
		for (OpeningHours oh : f.getOpeningHours()) {
			openingHours.add(new OpeningHoursDTO(oh));
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public MenuDTO getMenu() {
		return menu;
	}

	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public List<OpeningHoursDTO> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHoursDTO> openingHours) {
		this.openingHours = openingHours;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
