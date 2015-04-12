package cz.cvut.fel.jee.gourmeter.dto;

import java.util.List;

import cz.cvut.fel.jee.gourmeter.bo.CateringFacility;

/**
 * Used for creating catering facility. It differs form CateringFacilityDTO by attributes address, categories and facilityID.
 * 
 * @author Michal
 *
 */
public class CateringFacilityCreateDTO {

	private Long id;
	private String title;
	private String url;
	private String description;
	private Double latitude;
	private Double longitude;
	private List<Long> categories;
	private MenuDTO menu;
	private AddressDTO address;
	private List<TagDTO> tags;
	private List<OpeningHoursDTO> openingHours;

	public CateringFacilityCreateDTO() {
	}

//	public CateringFacilityCreateDTO(CateringFacility f) {
//		title = f.getName();
//		url = f.getUrl();
//		description = f.getDescription();
//		latitude = f.getLatitude();
//		longitude = f.getLongitude();
//		menu = new MenuDTO(f.getMenuFrom(), f.getMenuTo(), f.getMenuUrl());
//		address = new AddressDTO(f.getCity(), f.getStreet(), f.getHouseNumber());
//		categoryId = f.getCategories();
//		tags = DTOUtils.getFacilityTags(f);
//		openingHours = DTOUtils.getHoursDTO(f);
//	}

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

	public List<Long> getCategories() {
		return categories;
	}

	public void setCategories(List<Long> categories) {
		this.categories = categories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
