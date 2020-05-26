package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

public class NonexistentCityException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonexistentCityException(String errorMessage) {
		super(errorMessage);
	}

}
