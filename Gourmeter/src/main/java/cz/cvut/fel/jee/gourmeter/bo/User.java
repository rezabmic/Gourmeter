package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Jan Srogl
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "User.findByLogin", query = "select e from User e where e.login = :login")
})
@Table(name = "users")
public class User extends AbstractBusinessObject {
	
	private static final long serialVersionUID = 11130L;

	@NotNull
	@Column(unique = true)
	private String login;

	@Column(name= "passwd_hash")
	private String passwdHash;

	@NotNull
	@Column(unique = true)
	private String nickname;

	@Email
	private String email;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
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
