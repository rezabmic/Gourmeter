package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.User;

public class UserDTO {

	private Long userId;
	private String login;
	private String fullName;
	private String email;
	private String city;

	public UserDTO() {
	}

	public UserDTO(User u) {
		userId = u.getId();
		login = u.getLogin();
		fullName = u.getFullName();
		email = u.getEmail();
		city = u.getCity();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
