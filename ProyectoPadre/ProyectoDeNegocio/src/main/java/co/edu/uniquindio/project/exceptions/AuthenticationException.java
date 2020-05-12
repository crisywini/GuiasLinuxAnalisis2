package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class AuthenticationException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class AuthenticationException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param errorMessage the error message
	 */
	public AuthenticationException(String errorMessage) {
		super(errorMessage);
	}

}
