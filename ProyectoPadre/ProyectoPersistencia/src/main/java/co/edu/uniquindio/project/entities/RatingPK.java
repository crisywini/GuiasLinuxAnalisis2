package co.edu.uniquindio.project.entities;

import java.io.Serializable;

import java.lang.String;
import javax.persistence.*;

@Embeddable
public class RatingPK implements Serializable {

	@Column(name = "client_code", nullable = false)
	private String clientCode;
	@Column(name = "project_code", nullable = false)
	private int projectCode;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public RatingPK() {
		super();
	}

	/**
	 * Constructor method
	 * 
	 * @param clientCode  from relationship with {@link Client} not nullable
	 * @param projectCode from relationship with {@link Project} not nullable
	 */
	public RatingPK(String clientCode, int projectCode) {
		super();
		this.clientCode = clientCode;
		this.projectCode = projectCode;
	}

	public String getClientCode() {
		return this.clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public int getProjectCode() {
		return this.projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientCode == null) ? 0 : clientCode.hashCode());
		result = prime * result + projectCode;
		return result;
	}

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

	@Override
	public String toString() {
		return "RatingPK [clientCode=" + clientCode + ", projectCode=" + projectCode + "]";
	}

}
