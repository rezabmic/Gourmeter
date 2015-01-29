package cz.cvut.fel.jee.gourmeter.ejb;

import java.security.MessageDigest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		u.setCity(user.getCity());
		u.setEmail(user.getEmail());
		u.setFullName(user.getFullName());
		u.setLogin(user.getLogin());
		u.setPasswdHash(getPasswordHash(user.getPassword()));
		u.setUserRole(dao.findRoleByName(UserRole.USER_ROLE));

		em.persist(u);
		return new UserDTO(u);
	}

	@Override
	public UserDTO editUserAccount(UserDTO user) throws SignInException {
		User u = dao.findUserByLogin(user.getLogin());
		u.setCity(user.getCity());
		u.setEmail(user.getEmail());
		u.setFullName(user.getFullName());
		u.setLogin(user.getLogin());
		u.setPasswdHash(getPasswordHash(user.getPassword()));

		em.merge(u);
		return new UserDTO(u);
	}

	// TODO tohle my mel resit JAAS
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

}
