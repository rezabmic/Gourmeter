package cz.cvut.fel.jee.gourmeter.dto;

public class AddressDTO {

	private String city;
	private String street;
	private String houseNumber;

	public AddressDTO() {
	}

	public AddressDTO(String city, String street, String houseNumber) {
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

}
