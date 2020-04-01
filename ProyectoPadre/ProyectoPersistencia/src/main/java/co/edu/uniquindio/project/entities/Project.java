package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {

	@Id
	@Column(name = "code", nullable = false)
	private int code;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "latitude", nullable = false)
	private double latitude;
	@Column(name = "length", nullable = false)
	private double length;
	@Lob
	@Column(name = "description", nullable = false)
	private String description;
	@ManyToMany(mappedBy = "projects") // Esta es la entidad no propietaria
	@JoinColumn(name = "associated_services")
	private List<Service> services;
	@ManyToOne
	@JoinColumn(name = "associated_estate_agency")
	private EstateAgency estateAgency;// Esta es la entidad propietaria
	@ManyToOne
	@JoinColumn(name = "associated_city")
	private City city;
	@OneToMany(mappedBy = "project") // Esta es la entidad no propietaria
	@JoinColumn(name = "associated_dwellings")
	private List<Dwelling> dwellings;
	@OneToMany(mappedBy = "projectCode") // Esta es la entidad no propietaria
	@JoinColumn(name = "associated_contacts")
	private List<Contact> contacts;
	@OneToMany(mappedBy = "projectCode") // Esta es la entidad no propietaria
	@JoinColumn(name = "associated_comments")
	private List<Comment> comments;
	@OneToMany(mappedBy = "projectRating") // Esta es la entidad no propietaria
	@JoinColumn(name = "associated_ratings")
	private List<Rating> ratings;
	@ManyToMany(mappedBy = "favoriteProjects") // Esta es la entidad no propietaria
	@JoinColumn(name = "associated_favorite_clients")
	private List<Client> favoriteClients;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public Project() {
		super();
	}

	/**
	 * Constructor method
	 * 
	 * @param code            from {@link Project} primary key and not nullable
	 * @param name            from {@link Project} not nullable
	 * @param latitude        from {@link Project} not nullable
	 * @param length          from {@link Project} not nullable
	 * @param description     from {@link Project} big description and not nullable
	 * @param services        from relationship with {@link Service}
	 * @param estateAgency    relationship with {@link EstateAgency}
	 * @param city            relationship with {@link City}
	 * @param dwellings       from relationship with {@link Dwelling}
	 * @param contacts        from relationship with {@link Contact}
	 * @param comments        from relationship with {@link Comment}
	 * @param ratings         from relationship with {@link Rating}
	 * @param favoriteClients from relationship with {@link Client}
	 */
	public Project(int code, String name, double latitude, double length, String description, List<Service> services,
			EstateAgency estateAgency, City city, List<Dwelling> dwellings, List<Contact> contacts,
			List<Comment> comments, List<Rating> ratings, List<Client> favoriteClients) {
		this.code = code;
		this.name = name;
		this.latitude = latitude;
		this.length = length;
		this.description = description;
		this.services = services;
		this.estateAgency = estateAgency;
		this.city = city;
		this.dwellings = dwellings;
		this.contacts = contacts;
		this.comments = comments;
		this.ratings = ratings;
		this.favoriteClients = favoriteClients;
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

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Client> getFavoriteClients() {
		return favoriteClients;
	}

	public void setFavoriteClients(List<Client> favoriteClients) {
		this.favoriteClients = favoriteClients;
	}

}
