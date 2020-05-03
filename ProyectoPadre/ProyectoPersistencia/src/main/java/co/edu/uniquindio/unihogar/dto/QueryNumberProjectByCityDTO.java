package co.edu.uniquindio.unihogar.dto;

import java.io.Serializable;

/**
 * The class QueryNumberProjectByCityDTO 
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class QueryNumberProjectByCityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The number projects. */
	private long numberProjects;

	/** The name city. */
	private String nameCity;

	/**
	 * Instantiates a new query number project by city DTO.
	 *
	 * @param numberProjects the number projects
	 * @param nameCity       the name city
	 */
	public QueryNumberProjectByCityDTO(long numberProjects, String nameCity) {
		super();
		this.numberProjects = numberProjects;
		this.nameCity = nameCity;
	}

	/**
	 * Gets the number projects.
	 *
	 * @return the number projects
	 */
	public long getNumberProjects() {
		return numberProjects;
	}

	/**
	 * Sets the number projects.
	 *
	 * @param numberProjects the new number projects
	 */
	public void setNumberProjects(long numberProjects) {
		this.numberProjects = numberProjects;
	}

	/**
	 * Gets the name city.
	 *
	 * @return the name city
	 */
	public String getNameCity() {
		return nameCity;
	}

	/**
	 * Sets the name city.
	 *
	 * @param nameCity the new name city
	 */
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "QueryNumberProjectByCityDTO [numberProjects=" + numberProjects + ", nameCity=" + nameCity + "]";
	}
}
