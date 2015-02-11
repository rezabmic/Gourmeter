package cz.cvut.fel.jee.gourmeter.rest.security;

import java.io.IOException;
import java.io.Serializable;

import org.jboss.resteasy.util.Base64;

/**
 * Wrapper for Base64 encoded credentials.
 *
 */
public class CredentialsWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loginEnc;
	private String passwordEnc;

	public CredentialsWrapper() {
	}

	public String getLoginEnc() {
		return loginEnc;
	}

	public void setLoginEnc(String loginEnc) {
		this.loginEnc = loginEnc;
	}

	public String getPasswordEnc() {
		return passwordEnc;
	}

	public void setPasswordEnc(String passwordEnc) {
		this.passwordEnc = passwordEnc;
	}

	public String getDecodedLogin() throws IOException {
		return new String(Base64.decode(loginEnc));
	}

	public String getDecodedPassword() throws IOException {
		return new String(Base64.decode(passwordEnc));
	}

}
