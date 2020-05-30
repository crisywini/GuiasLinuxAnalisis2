package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class NonexistentCityException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class NonexistentCityException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new nonexistent city exception.
	 *
	 * @param errorMessage the error message
	 */
	public NonexistentCityException(String errorMessage) {
		super(errorMessage);
	}
}
