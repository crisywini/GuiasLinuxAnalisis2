package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Service
 *
 */
@Entity

public class Service implements Serializable {

	@Id
	@Column(name = "code", nullable = false)
	private int code;
	@Column(name = "name", nullable = false, unique = true) // ask for the unique
	private String name;
	@ManyToMany
	@JoinColumn(name = "associated_projects")
	private List<Project> projects;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public Service() {
		super();
	}

	/**
	 * Constructor method
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
	 * Constructor method
	 * 
	 * @param code     from {@link Service} primary key and not nullable
	 * @param name     from {@link Service} not nullable and unique
	 */
	public Service(int code, String name) {
		this.code = code;
		this.name = name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		Service other = (Service) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [code=" + code + ", name=" + name + ", projects=" + projects + "]";
	}

}
