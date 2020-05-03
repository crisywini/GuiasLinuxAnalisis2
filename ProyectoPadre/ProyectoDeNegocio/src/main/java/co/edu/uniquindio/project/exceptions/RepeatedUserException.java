package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class RepeatedUserException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class RepeatedUserException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new repeated user exception.
	 *
	 * @param errorMessage the error message
	 */
	public RepeatedUserException(String errorMessage) {
		super(errorMessage);
	}
}
