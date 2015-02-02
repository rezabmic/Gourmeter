package cz.cvut.fel.jee.gourmeter.rest;

import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cz.cvut.fel.jee.gourmeter.bo.User;
import cz.cvut.fel.jee.gourmeter.dto.UserDTO;
import cz.cvut.fel.jee.gourmeter.ejb.DataSessionLocal;
import cz.cvut.fel.jee.gourmeter.ejb.UserSessionLocal;
import cz.cvut.fel.jee.gourmeter.exception.SignInException;
import cz.cvut.fel.jee.gourmeter.rest.security.CredentialsWrapper;

@Path("/user")
@ApplicationScoped
public class UserRESTServiceImpl implements UserRESTService {

	@Inject
	private UserSessionLocal userSession;

	@Inject
	private DataSessionLocal dao;
	
	@POST
	@Path("/auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public UserDTO signIn(CredentialsWrapper cw) {
		try {
			String login = cw.getDecodedLogin();
			String password = cw.getDecodedPassword();
			return userSession.authenticateUser(login, password);
		} catch (IOException | SignInException e) {
			throw new BadRequestException("Bad credentials");
		}
	}

	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"user", "tester", "admin"})
	public UserDTO editAccount(UserDTO userDto) {
		try {
			return userSession.editUserAccount(userDto);
		} catch (SignInException e) {
			throw new BadRequestException("Bad credentials");
		}
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public UserDTO createAccount(UserDTO userDto) {
		try {
			return userSession.createNewUser(userDto);
		} catch (SignInException e) {
			throw new BadRequestException("Bad credentials");
		}
	}
	
	@GET
	@Path("/testUniqueLogin")
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean testUniqueLogin(@QueryParam("login") String login) {
		User user = dao.findUserByLogin(login);
		return user == null; // if user was found, login is not unique -> return false
	}
	

}
