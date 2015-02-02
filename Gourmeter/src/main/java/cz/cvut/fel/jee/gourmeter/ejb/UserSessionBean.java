package cz.cvut.fel.jee.gourmeter.ejb;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.bo.User;
import cz.cvut.fel.jee.gourmeter.bo.UserRole;
import cz.cvut.fel.jee.gourmeter.dto.UserDTO;
import cz.cvut.fel.jee.gourmeter.exception.SignInException;

@Stateless
public class UserSessionBean implements UserSessionLocal {

	private static final Logger log = LoggerFactory
			.getLogger(UserSessionBean.class);

	@PersistenceContext(unitName = "GourmeterPU")
	private EntityManager em;

	@EJB
	private DataSessionLocal dao;

	@Override
	public UserDTO createNewUser(UserDTO user) throws SignInException {
		User u = new User();
		setUserAttributes(user, u);
		setUserPassword(getDecodedPassword(user.getPassword()), u);

		u.setUserRole(dao.findRoleByName(UserRole.USER_ROLE));
		em.persist(u);
		return new UserDTO(u);
	}

	@Override
	public UserDTO editUserAccount(UserDTO user) throws SignInException {
		User u = dao.findUserByLogin(user.getLogin());
		setUserAttributes(user, u);
		if (user.getPassword() != null) {
			if (getPasswordHash(getDecodedPassword(user.getPassword())).equals(u.getPasswdHash())) {
				setUserPassword(getDecodedPassword(user.getNewPassword()), u);
			} else {
				throw new SignInException("Bad password.");
			}
		}

		em.merge(u);
		return new UserDTO(u);
	}

	@Override
	public UserDTO authenticateUser(String login, String password)
			throws SignInException {
		User u = dao.findUserByLogin(login);
		if (u == null) {
			throw new SignInException("User " + login + " not found.");
		}
		String passwdHash = getPasswordHash(password);
		if (!u.getPasswdHash().equals(passwdHash)) {
			throw new SignInException("Bad password.");
		}
		return new UserDTO(u);
	}

	@Override
	public UserDTO authenticateUser(String login,
									String password,
									Set<String> allowedRoles)
			throws SignInException {
		User u = dao.findUserByLogin(login);
		if (u == null) {
			throw new SignInException("User " + login + " not found.");
		}
		String passwdHash = getPasswordHash(password);
		if (!u.getPasswdHash().equals(passwdHash)) {
			throw new SignInException("Bad password.");
		}
		if (!allowedRoles.contains(u.getUserRole().getName())) {
			throw new SignInException("Unauthorized.");
		}
		return new UserDTO(u);
	}

	@Override
	public UserRole getUsersRole(Long userId) {
		User u = em.find(User.class, userId);
		return u.getUserRole();
	}

	private String getPasswordHash(String password) throws SignInException {
		if (password == null) {
			throw new IllegalArgumentException("User's password cannot be null");
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] byteData = md.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SignInException("Cannot digest password.");
		}
	}

	private void setUserAttributes(UserDTO user, User u) {
		u.setCity(user.getCity());
		u.setEmail(user.getEmail());
		u.setNickname(user.getNickname());
		u.setLogin(user.getLogin());
	}

	private void setUserPassword(String password, User u) throws SignInException {
		String encPasswd = getDecodedPassword(password);
		u.setPasswdHash(getPasswordHash(encPasswd));
	}

	private String getDecodedPassword(String password) throws SignInException {
		try {
			return new String(Base64.decode(password));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SignInException(e);
		}
	}

}
