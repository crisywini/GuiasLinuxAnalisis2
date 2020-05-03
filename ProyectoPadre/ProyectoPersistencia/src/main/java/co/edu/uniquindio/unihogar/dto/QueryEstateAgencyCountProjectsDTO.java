package co.edu.uniquindio.unihogar.dto;

import java.io.Serializable;

import co.edu.uniquindio.unihogar.entities.EstateAgency;

/**
 * The class QueryEstateAgencyCountProjectsDTO
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class QueryEstateAgencyCountProjectsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** The estate agency. */
	private EstateAgency estateAgency;
	
	/** The count projects. */
	private Long countProjects;

	/**
	 * Instantiates a new query estate agency count projects DTO.
	 *
	 * @param estateAgency the estate agency
	 * @param countProjects the count projects
	 */
	public QueryEstateAgencyCountProjectsDTO(EstateAgency estateAgency, Long countProjects) {
		this.estateAgency = estateAgency;
		this.countProjects = countProjects;
	}

	/**
	 * Gets the estate agency.
	 *
	 * @return the estate agency
	 */
	public EstateAgency getEstateAgency() {
		return estateAgency;
	}

	/**
	 * Sets the estate agency.
	 *
	 * @param estateAgency the new estate agency
	 */
	public void setEstateAgency(EstateAgency estateAgency) {
		this.estateAgency = estateAgency;
	}

	/**
	 * Gets the count projects.
	 *
	 * @return the count projects
	 */
	public Long getCountProjects() {
		return countProjects;
	}

	/**
	 * Sets the count projects.
	 *
	 * @param countProjects the new count projects
	 */
	public void setCountProjects(Long countProjects) {
		this.countProjects = countProjects;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "QueryEstateAgencyCountProjectsDTO [estateAgency=" + estateAgency.getCode() + ", countProjects="
				+ countProjects + "]";
	}
}
