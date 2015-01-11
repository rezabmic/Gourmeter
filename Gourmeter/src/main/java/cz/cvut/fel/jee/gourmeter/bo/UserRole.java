package cz.cvut.fel.jee.gourmeter.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@Table(name = "user_role")
public class UserRole extends AbstractBusinessObject {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column
	private String name;

	@OneToMany(mappedBy = "userRole")
	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		if (users == null) {
			users = new ArrayList<>();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
