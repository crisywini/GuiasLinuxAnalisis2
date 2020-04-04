package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EstateAgency
 * 
 * @author crisisanchezp
 */
@Entity

public class EstateAgency extends User implements Serializable {

	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "name", nullable = false)
	private String name;
	@ElementCollection
	@JoinColumn(name = "phone_numbers")
	private Map<String, String> phoneNumbers;
	@ElementCollection
	@JoinColumn(name = "advisors")
	private Map<String, String> advisors;// Asesor inmobiliario el cual el primero es el nombre y el segundo es el
											// numero
											// de telefono
	@OneToMany(mappedBy = "estateAgency") // Esta entidad es la no propietaria
	@JoinColumn(name = "associated_projects")
	private List<Project> projects;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public EstateAgency() {
		super();
	}

	/**
	 * 
	 * @param code     from {@link User} primary key not nullable
	 * @param email    from {@link User} unique not nullable
	 * @param password from {@link User} not nullable
	 * @param address  from {@link EstateAgency} not nullable
	 * @param projects from relationship with {@link Project}
	 */
	public EstateAgency(String name, String code, String email, String password, String address,
			List<Project> projects) {
		super(code, email, password);
		this.address = address;
		this.projects = projects;
		this.name = name;
		advisors = new HashMap<String, String>();
		phoneNumbers = new HashMap<String, String>();
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Map<String, String> getAdvisors() {
		return advisors;
	}

	public void setAdvisors(Map<String, String> advisors) {
		this.advisors = advisors;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EstateAgency [address=" + address + ", name=" + name + ", phoneNumbers=" + phoneNumbers + ", advisors="
				+ advisors + ", projects=" + projects + "]";
	}
	
}
