package co.edu.uniquindio.unihogar.dto;

import java.io.Serializable;

/**
 * The class QueryDwellingByProjectDTO
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class QueryDwellingByProjectDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** The number dwellings. */
	private long numberDwellings;
	
	/** The name project. */
	private String nameProject;
	
	/**
	 * Instantiates a new query dwelling by project DTO.
	 *
	 * @param numberDwellings the number dwellings
	 * @param nameProject the name project
	 */
	public QueryDwellingByProjectDTO(long numberDwellings, String nameProject) {
		super();
		this.numberDwellings = numberDwellings;
		this.nameProject = nameProject;
	}
	
	/**
	 * Gets the number dwellings.
	 *
	 * @return the number dwellings
	 */
	public long getNumberDwellings() {
		return numberDwellings;
	}
	
	/**
	 * Sets the number dwellings.
	 *
	 * @param numberDwellings the new number dwellings
	 */
	public void setNumberDwellings(long numberDwellings) {
		this.numberDwellings = numberDwellings;
	}
	
	/**
	 * Gets the name project.
	 *
	 * @return the name project
	 */
	public String getNameProject() {
		return nameProject;
	}
	
	/**
	 * Sets the name project.
	 *
	 * @param nameProject the new name project
	 */
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "QueryDwellingByProjectDTO [numberDwellings=" + numberDwellings + ", nameProject=" + nameProject + "]";
	}

}
