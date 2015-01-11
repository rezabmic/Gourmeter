
package cz.cvut.fel.jee.gourmeter.bo;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan Šrogl
 */
public class UserRole extends AbstractBusinessObject
{
    @Column(nullable = false)
    String name;
    @OneToMany(mappedBy = "userRole")
    Set<User> users;

    //GETTER & SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
