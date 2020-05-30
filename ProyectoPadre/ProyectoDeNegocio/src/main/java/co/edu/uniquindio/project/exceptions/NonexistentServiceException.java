package co.edu.uniquindio.project.exceptions;

/**
 * The class NonexistentServiceException
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class NonexistentServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new nonexistent service exception.
	 *
	 * @param errorMessage the error message
	 */
	public NonexistentServiceException(String errorMessage) {
		super(errorMessage);
	}
}
