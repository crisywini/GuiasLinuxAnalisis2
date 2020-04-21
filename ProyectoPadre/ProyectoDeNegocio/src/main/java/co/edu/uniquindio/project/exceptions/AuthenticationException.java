package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

public class AuthenticationException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthenticationException(String errorMessage) {
		super(errorMessage);
	}

}
