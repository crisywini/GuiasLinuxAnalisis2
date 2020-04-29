package co.edu.uniquindio.unihogar.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: City.
 * 
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
@NamedQueries({ @NamedQuery(name = City.GET_ALL_CITY, query = "SELECT c FROM City c"),
		@NamedQuery(name = City.GET_BY_CODE, query = "SELECT c FROM City c WHERE c.code =:codeCity"),
		@NamedQuery(name = City.GET_BY_NAME, query = "SELECT c FROM City c WHERE c.name = :nameCity")

})

public class City implements Serializable {

	/** The code. */
	@Id
	@Column(name = "code", nullable = false)
	private int code;

	/** The name. */
	@Column(name = "name", nullable = false)
	private String name;

	/** The projects. */
	@OneToMany(mappedBy = "city")
	@JoinColumn(name = "associated_projects")
	private List<Project> projects;

	private static final long serialVersionUID = 1L;

	// Queries
	public static final String GET_ALL_CITY = "GET_ALL_CITY";
	public static final String GET_BY_CODE = "GET_BY_CODE";
	public static final String GET_BY_NAME = "GET_BY_NAME";

	/**
	 * Default constructor method.
	 */
	public City() {
		super();
	}

	/**
	 * Constructor method from City.
	 *
	 * @param code     from {@link City} primary key and not nullable
	 * @param name     from {@link City} not nullable
	 * @param projects relationship with {@link Project}
	 */
	public City(int code, String name, List<Project> projects) {
		this.code = code;
		this.name = name;
		this.projects = projects;
	}

	/**
	 * Constructor method.
	 *
	 * @param code from {@link City} primary key and not nullable
	 * @param name from {@link City} not nullable
	 */
	public City(int code, String name) {
		this.code = code;
		this.name = name;
		projects = new LinkedList<Project>();
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

	@Override
	public String toString() {
		return "City [code=" + code + ", name=" + name + "]";
	}

}
