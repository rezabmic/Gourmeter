package cz.cvut.fel.jee.gourmeter.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Jan Srogl
 */
@Entity
@Table(name = "catering_facility")
@NamedQueries({
		@NamedQuery(name = "CateringFacility.findByName", query = "SELECT e FROM CateringFacility e WHERE e.name = :name"),
		@NamedQuery(name = "CateringFacility.findById", query = "SELECT e FROM CateringFacility e WHERE e.id = :id"),
		@NamedQuery(name = "CateringFacility.findByCoordinates", query = "SELECT e FROM CateringFacility e "
				+ "WHERE e.latitude < :latitudeMax AND e.latitude > :latitudeMin "
				+ "AND e.longitude < :longitudeMax AND e.longitude > :longitudeMin"),
		@NamedQuery(name = "CateringFacility.findByCoordinatesAndTag", query = "SELECT e FROM CateringFacility e "
				+ "WHERE e.latitude < :latitudeMax AND e.latitude > :latitudeMin "
				+ "AND e.longitude < :longitudeMax AND e.longitude > :longitudeMin "
				+ "AND :tag MEMBER OF e.tags") })
public class CateringFacility extends AbstractBusinessObject {

	private static final long serialVersionUID = 11170L;

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	private String url;

	@NotEmpty
	private String city;

	private String street;

	@Column(name="house_number")
	private String houseNumber;

	private String cityDistrict;

	private String menuUrl;

	@Temporal(TemporalType.TIME)
	private Date menuFrom;

	@Temporal(TemporalType.TIME)
	private Date menuTo;

	@NotNull
	private Double latitude;

	@NotNull
	private Double longitude;

	@ManyToMany
	@JoinTable(name = "catering_facility_categories", joinColumns = @JoinColumn(name = "facility_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OpeningHours> openingHours;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Recommendation> recommendations;

	@ManyToMany
	@JoinTable(name = "catering_facility_tags", joinColumns = @JoinColumn(name = "facility_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Category> getCategories() {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityDistrict() {
		return cityDistrict;
	}

	public void setCityDistrict(String cityDistrict) {
		this.cityDistrict = cityDistrict;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getMenuFrom() {
		return menuFrom;
	}

	public void setMenuFrom(Date menuFrom) {
		this.menuFrom = menuFrom;
	}

	public Date getMenuTo() {
		return menuTo;
	}

	public void setMenuTo(Date menuTo) {
		this.menuTo = menuTo;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public List<OpeningHours> getOpeningHours() {
		if (openingHours == null) {
			openingHours = new ArrayList<>();
		}
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHours> openingHours) {
		this.openingHours = openingHours;
	}

	public List<Tag> getTags() {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		return tags;
	}

	public List<Recommendation> getRecommendations() {
		if (recommendations == null) {
			recommendations = new ArrayList<>();
		}
		return recommendations;
	}

	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

}
