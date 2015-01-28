package cz.cvut.fel.jee.gourmeter.dto;

public class AddressDTO {

	private final String city;
	private final String street;
	private final String houseNumber;

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

}
