package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class NonexistentUserException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class NonexistentUserException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new nonexistent user exception.
	 *
	 * @param errorMessage the error message
	 */
	public NonexistentUserException(String errorMessage) {
		super(errorMessage);
	}
}
