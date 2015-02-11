package cz.cvut.fel.jee.gourmeter.rest;

import cz.cvut.fel.jee.gourmeter.dto.UserDTO;
import cz.cvut.fel.jee.gourmeter.rest.security.CredentialsWrapper;

public interface UserRESTService {
	
	public UserDTO signIn(CredentialsWrapper cw);

	public UserDTO editAccount(UserDTO userDto);
	
	public UserDTO createAccount(UserDTO userDto);

}
