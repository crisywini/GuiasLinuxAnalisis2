package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: City
 *
 */
@Entity

public class City implements Serializable {

	@Id
	@Column(name = "code", nullable = false)
	private int code;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(mappedBy = "city")
	@JoinColumn(name = "associated_projects")
	private List<Project> projects;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public City() {
		super();
	}

	/**
	 * Constructor method
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
	 * Constructor method
	 * 
	 * @param code from {@link City} primary key and not nullable
	 * @param name from {@link City} not nullable
	 */
	public City(int code, String name) {
		this.code = code;
		this.name = name;
		projects = new LinkedList<Project>();
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
