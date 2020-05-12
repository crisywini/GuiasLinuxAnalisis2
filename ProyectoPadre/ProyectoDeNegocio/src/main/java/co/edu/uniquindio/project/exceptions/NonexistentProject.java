package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

/**
 * The class NonexistentProject
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class NonexistentProject extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new nonexistent project.
	 *
	 * @param errorMessage the error message
	 */
	public NonexistentProject(String errorMessage) {
		super(errorMessage);
	}
}
