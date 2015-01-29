package cz.cvut.fel.jee.gourmeter.ejb;

import javax.ejb.Local;

import cz.cvut.fel.jee.gourmeter.bo.UserRole;
import cz.cvut.fel.jee.gourmeter.dto.UserDTO;
import cz.cvut.fel.jee.gourmeter.exception.SignInException;

@Local
public interface UserSessionLocal {

	public UserDTO createNewUser(UserDTO user) throws SignInException;
	
	public UserDTO editUserAccount(UserDTO user)  throws SignInException;
	
	public UserDTO authenticateUser(String login, String password)  throws SignInException;

	public UserRole getUsersRole(Long userId);
}
