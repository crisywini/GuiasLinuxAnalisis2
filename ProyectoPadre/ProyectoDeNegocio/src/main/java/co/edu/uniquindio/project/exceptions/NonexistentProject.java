package co.edu.uniquindio.project.exceptions;

import java.io.Serializable;

public class NonexistentProject extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NonexistentProject(String errorMessage) {
		super(errorMessage);
	}

}
