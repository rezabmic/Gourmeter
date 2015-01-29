package cz.cvut.fel.jee.gourmeter.exception;

public class SignInException extends Exception {

	private static final long serialVersionUID = 1L;

	public SignInException() {
		super();
	}

	public SignInException(String message, Throwable cause) {
		super(message, cause);
	}

	public SignInException(String message) {
		super(message);
	}

	public SignInException(Throwable cause) {
		super(cause);
	}

}
