package cz.cvut.fel.jee.gourmeter.dto;

import cz.cvut.fel.jee.gourmeter.bo.User;

public class UserDTO {

	private Long userId;
	private String login;
	private String fullName;
	private String email;
	private String city;
	
	/**
	 * Password is send from client to server only in case of its change.
	 * It should be encoded (base64).
	 */
	private String password;
	private String newPassword;

	public UserDTO() {
	}

	public UserDTO(User u) {
		userId = u.getId();
		login = u.getLogin();
		fullName = u.getFullName();
		email = u.getEmail();
		city = u.getCity();
		password = null;
		newPassword = null;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
