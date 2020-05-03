package co.edu.uniquindio.unihogar.entities;

import java.io.Serializable;

import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RatingPK.
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Embeddable
public class RatingPK implements Serializable {

	/** The client code. */
	@Column(name = "client_code", nullable = false)
	private String clientCode;

	/** The project code. */
	@Column(name = "project_code", nullable = false)
	private int projectCode;

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public RatingPK() {
		super();
	}

	/**
	 * Constructor method from RatingPK.
	 *
	 * @param clientCode  from relationship with {@link Client} not nullable
	 * @param projectCode from relationship with {@link Project} not nullable
	 */
	public RatingPK(String clientCode, int projectCode) {
		super();
		this.clientCode = clientCode;
		this.projectCode = projectCode;
	}

	/**
	 * Gets the client code.
	 *
	 * @return the client code
	 */
	public String getClientCode() {
		return this.clientCode;
	}

	/**
	 * Sets the client code.
	 *
	 * @param clientCode the new client code
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * Gets the project code.
	 *
	 * @return the project code
	 */
	public int getProjectCode() {
		return this.projectCode;
	}

	/**
	 * Sets the project code.
	 *
	 * @param projectCode the new project code
	 */
	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientCode == null) ? 0 : clientCode.hashCode());
		result = prime * result + projectCode;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingPK other = (RatingPK) obj;
		if (clientCode == null) {
			if (other.clientCode != null)
				return false;
		} else if (!clientCode.equals(other.clientCode))
			return false;
		if (projectCode != other.projectCode)
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RatingPK [clientCode=" + clientCode + ", projectCode=" + projectCode + "]";
	}

}
