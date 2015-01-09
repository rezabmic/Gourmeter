
package cz.cvut.fel.jee.gourmeter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jan Šrogl
 */
@Entity
@Table(name = "Users")
public class User extends AbstractBusinessObject
{
    @Column(nullable = false)
    String login;
    @Column(nullable = false)    
    String passwdHash;
    @Column(nullable = true)    
    String fullName;
    @OneToMany            
    UserRole userRole;
    
    //GETTER & SETTER
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
