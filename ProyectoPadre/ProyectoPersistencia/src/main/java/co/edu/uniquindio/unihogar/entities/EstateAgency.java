package co.edu.uniquindio.unihogar.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EstateAgency.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
@NamedQueries({
	@NamedQuery(name = EstateAgency.GET_ALL_ESTATE_AGENCY, query = "SELECT ea FROM EstateAgency ea"),
	@NamedQuery(name = EstateAgency.GET_ALL_ESTATE_AGENCY_PROJECTS, query="SELECT ea.code, p FROM EstateAgency ea LEFT JOIN ea.projects p"),
})
public class EstateAgency extends User implements Serializable {

	/** The address. */
	@Column(name = "address", nullable = false)
	private String address;

	/** The name. */
	@Column(name = "name", nullable = false)
	private String name;

	/** The phone numbers. */
	@ElementCollection
	@JoinColumn(name = "phone_numbers")
	private Map<String, String> phoneNumbers;

	/** The advisors. */
	@ElementCollection
	@JoinColumn(name = "advisors")
	private Map<String, String> advisors;// Asesor inmobiliario el cual el primero es el nombre y el segundo es el
											// numero de telefono
	/** The projects. */
	@OneToMany(mappedBy = "estateAgency") // Esta entidad es la no propietaria
	@JoinColumn(name = "associated_projects")
	private List<Project> projects;

	//Queries
	public static final String GET_ALL_ESTATE_AGENCY = "GET_ALL_ESTATE_AGENCY";
	public static final String GET_ALL_ESTATE_AGENCY_PROJECTS = "GET_ALL_ESTATE_AGENCY_PROJECTS";
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public EstateAgency() {
		super();
	}

	/**
	 * Constructor method from estate agency.
	 *
	 * @param name     the name
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

	/**
	 * Constructor method from estate agency.
	 *
	 * @param name     the name
	 * @param code     from {@link User} primary key not nullable
	 * @param email    from {@link User} unique not nullable
	 * @param password from {@link User} not nullable
	 * @param address  from {@link EstateAgency} not nullable
	 */
	public EstateAgency(String name, String code, String email, String password, String address) {
		super(code, email, password);
		this.address = address;
		this.name = name;
		advisors = new HashMap<String, String>();
		phoneNumbers = new HashMap<String, String>();
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the phone numbers.
	 *
	 * @return the phone numbers
	 */
	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * Sets the phone numbers.
	 *
	 * @param phoneNumbers the phone numbers
	 */
	public void setPhoneNumbers(Map<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	/**
	 * Gets the advisors.
	 *
	 * @return the advisors
	 */
	public Map<String, String> getAdvisors() {
		return advisors;
	}

	/**
	 * Sets the advisors.
	 *
	 * @param advisors the advisors
	 */
	public void setAdvisors(Map<String, String> advisors) {
		this.advisors = advisors;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EstateAgency [address=" + address + ", name=" + name + ", phoneNumbers=" + phoneNumbers + ", advisors="
				+ advisors + ", projects=" + projects + "]";
	}

}
