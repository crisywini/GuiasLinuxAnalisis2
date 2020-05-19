package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

public class RepeatedProjectException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RepeatedProjectException(String errorMessage) {
		super(errorMessage);
	}

}
