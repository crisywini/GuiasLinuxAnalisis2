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
	private int code;
	private String name;
	@ManyToMany
	private List<Project> projects;
	private static final long serialVersionUID = 1L;

	public Service() {
		super();
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
