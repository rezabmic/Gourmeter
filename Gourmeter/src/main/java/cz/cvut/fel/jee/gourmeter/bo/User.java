package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@Table(name = "users")
public class User extends AbstractBusinessObject {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name= "login")
	private String login;

	@Column(name= "passwd_hash")
	private String passwdHash;

	@NotNull
	@Column(name = "full_name")
	private String fullName;

	@Email
	@Column(name = "email")
	private String email;

	@Column(name = "city")
	private String city;

	@ManyToOne
	private UserRole userRole;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
