package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

public class NonexistentUserException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NonexistentUserException(String errorMessage) {
		super(errorMessage);
	}
	

}
