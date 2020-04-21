package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

public class RepeatedUserException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RepeatedUserException(String errorMessage) {
		super(errorMessage);
	}

}
