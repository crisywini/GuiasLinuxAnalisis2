package co.edu.uniquindio.project.exceptions;

public class NonexistentServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonexistentServiceException(String errorMessage) {
		super(errorMessage);
	}

}
