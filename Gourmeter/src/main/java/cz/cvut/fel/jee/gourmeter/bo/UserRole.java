package cz.cvut.fel.jee.gourmeter.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "UserRole.findByName", query = "select e from UserRole e where e.name = :name")
})
@Table(name = "user_role")
public class UserRole extends AbstractBusinessObject implements Serializable{
	
	private static final long serialVersionUID = 11131L;
	
	public static final String USER_ROLE = "user";
	public static final String TESTER_ROLE = "tester";
	public static final String ADMIN_ROLE = "admin";
	
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
