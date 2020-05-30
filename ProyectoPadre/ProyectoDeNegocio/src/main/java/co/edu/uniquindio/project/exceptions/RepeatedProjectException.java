package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class RepeatedProjectException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class RepeatedProjectException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new repeated project exception.
	 *
	 * @param errorMessage the error message
	 */
	public RepeatedProjectException(String errorMessage) {
		super(errorMessage);
	}
}
