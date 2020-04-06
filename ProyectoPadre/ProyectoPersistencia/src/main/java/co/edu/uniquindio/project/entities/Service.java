package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Service.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
public class Service implements Serializable {

	/** The code. */
	@Id
	@Column(name = "code", nullable = false)
	private int code;

	/** The name. */
	@Column(name = "name", nullable = false, unique = true) // ask for the unique
	private String name;

	/** The projects. */
	@ManyToMany
	@JoinColumn(name = "associated_projects")
	private List<Project> projects;

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public Service() {
		super();
	}

	/**
	 * Constructor method from Service.
	 * 
	 * @param code     from {@link Service} primary key and not nullable
	 * @param name     from {@link Service} not nullable and unique
	 * @param projects relationship with {@link Project}
	 */
	public Service(int code, String name, List<Project> projects) {
		this.code = code;
		this.name = name;
		this.projects = projects;
	}

	/**
	 * Constructor method from Service.
	 * 
	 * @param code from {@link Service} primary key and not nullable
	 * @param name from {@link Service} not nullable and unique
	 */
	public Service(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * Sets the projects.
	 *
	 * @param projects the new projects
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
		result = prime * result + code;
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
		Service other = (Service) obj;
		if (code != other.code)
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
		return "Service [code=" + code + ", name=" + name + ", projects=" + projects + "]";
	}

}
