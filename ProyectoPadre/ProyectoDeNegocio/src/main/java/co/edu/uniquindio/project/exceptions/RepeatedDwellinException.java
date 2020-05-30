package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class RepeatedDwellinException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class RepeatedDwellinException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new repeated dwellin exception.
	 *
	 * @param errorMessage the error message
	 */
	public RepeatedDwellinException(String errorMessage) {
		super(errorMessage);
	}
}
